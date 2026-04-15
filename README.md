```markdown
# Movement Order Architecture

Authoritative architecture and integration notes for the Movement Order workflow, designed for use with GitHub Copilot inside the repository.

---

## 1. Tech stack

### 1.1 Backend

- **Language / Runtime:** Java on Tomcat
- **Web framework:** Struts 6.x
  - XML‑configured actions (no annotations)
  - Classic MVC pattern
- **View composition:** Apache Tiles
- **Persistence:** MySQL (JDBC, no ORM)
- **Key domains:**
  - Reservations
  - Dispatch
  - Equipment
  - Equipment location history
  - Equipment availability (derived)
  - Movement Orders (this document)

### 1.2 Frontend

- **Templating:** JSP (under `/WEB-INF/jsp`)
- **CSS framework:** Bootstrap **4.6.2**
  - Uses `data-toggle="modal"`, `data-target="#..."`, `.close` buttons
  - jQuery‑based components
- **JS libraries:**
  - jQuery 3.6.0
  - Popper.js 1.16.1
  - DataTables (CSS/JS)
- **Icons:** Font Awesome 5.15.1
- **Custom assets:**
  - `/mcquaids/css/mcquaids.css`
  - `/mcquaids/javascript/navbar.js`
  - `/mcquaids/javascript/FormValidations.js`
  - `/mcquaids/javascript/mcquaids.js`
  - `/mcquaids/javascript/common/workflow.js`

### 1.3 Infrastructure

- **Front proxy:** Apache 2.4
  - Routes to Tomcat
  - Serves static assets
- **App server:** Tomcat
- **Uploads:**
  - PHP module‑based upload handler (replaced Python CGI)
  - Files currently stored on proxy server
  - TODO: move to durable shared/object storage

### 1.4 Architectural preferences

- Explicit, maintainable models
- Single‑source‑of‑truth fields
- Derived state engines (availability, status)
- Event‑driven workflows
- Scenario‑driven modeling
- Avoid scattered logic, UI hacks, ambiguous status codes
- Prefer clean schema migrations and clear separation of concerns

---

## 2. Movement Order roadmap

High‑level development roadmap for the Movement Order feature.

1. **Database layer**
   - Create Movement Order tables
   - Align `EquipmentNumber` type with `equipment` table (`INT(10)` signed)
   - Add foreign keys and indexes

2. **DAO layer**
   - Implement DAOs for header, lines, swap links, event log
   - Use existing JDBC patterns

3. **Service layer**
   - Implement `MovementOrderService`
   - Integrate with `DispatchService` and location/availability services

4. **Struts actions**
   - Create actions for list, create, review, confirm, detail, swap detail, bulk detail, status updates

5. **Tiles + JSP UI**
   - Define Tiles views for all Movement Order screens
   - Implement JSPs using Bootstrap 4.6.2

6. **Status engine**
   - Implement header status derived from line statuses

7. **Validation rules**
   - Enforce reservation, dispatch, and equipment constraints before creating Movement Orders

8. **Completion workflow**
   - Wire dispatch completion to Movement Order line/header updates, location history, and availability

9. **Reporting / enhancements (future)**
   - Movement analytics, yard balancing, SLA tracking, attachments, etc.

---

## 3. Database schema

### 3.1 movement_order_header

Represents the business intent of a movement.

```sql
CREATE TABLE movement_order_header (
    MovementOrderID     BIGINT PRIMARY KEY AUTO_INCREMENT,
    RequestedBy         VARCHAR(100) NOT NULL,
    RequestedDateTime   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Priority            ENUM('NORMAL','URGENT') NOT NULL DEFAULT 'NORMAL',
    MovementType        ENUM(
                             'REPOSITION',
                             'INSPECTION',
                             'CLEANING',
                             'MAINTENANCE',
                             'CUSTOMER_REQUEST',
                             'SWAP',
                             'BULK'
                         ) NOT NULL,
    ReasonCode          VARCHAR(100) NULL,
    Notes               TEXT NULL,

    Status              ENUM(
                             'NEW',
                             'IN_PROGRESS',
                             'COMPLETED',
                             'CANCELLED'
                         ) NOT NULL DEFAULT 'NEW'
);
```

### 3.2 movement_order_line

Represents a single equipment movement within an order.

```sql
CREATE TABLE movement_order_line (
    MovementOrderLineID BIGINT PRIMARY KEY AUTO_INCREMENT,
    MovementOrderID     BIGINT NOT NULL,
    EquipmentNumber     INT(10) NOT NULL,  -- signed, matches equipment.EquipmentNumber

    TargetLocationType  ENUM('ON_PREMISE','CUSTOMER_SITE') NOT NULL,
    TargetYardID        BIGINT NULL,
    TargetStreet        VARCHAR(200) NULL,
    TargetCity          VARCHAR(100) NULL,
    TargetProvince      VARCHAR(50) NULL,
    TargetPostal        VARCHAR(20) NULL,
    TargetCountry       VARCHAR(50) NULL,

    DispatchID          BIGINT NULL,

    LineStatus          ENUM(
                             'NEW',
                             'ASSIGNED',
                             'IN_TRANSIT',
                             'COMPLETED',
                             'FAILED',
                             'CANCELLED'
                         ) NOT NULL DEFAULT 'NEW',

    FOREIGN KEY (MovementOrderID) REFERENCES movement_order_header(MovementOrderID),
    FOREIGN KEY (EquipmentNumber) REFERENCES equipment(EquipmentNumber)
);

CREATE INDEX idx_mol_order ON movement_order_line (MovementOrderID);
CREATE INDEX idx_mol_equipment ON movement_order_line (EquipmentNumber);
CREATE INDEX idx_mol_dispatch ON movement_order_line (DispatchID);
```

### 3.3 movement_order_swap_link

Links two lines together for a swap.

```sql
CREATE TABLE movement_order_swap_link (
    SwapLinkID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    MovementOrderID     BIGINT NOT NULL,
    LineAID             BIGINT NOT NULL,
    LineBID             BIGINT NOT NULL,

    FOREIGN KEY (MovementOrderID) REFERENCES movement_order_header(MovementOrderID),
    FOREIGN KEY (LineAID) REFERENCES movement_order_line(MovementOrderLineID),
    FOREIGN KEY (LineBID) REFERENCES movement_order_line(MovementOrderLineID)
);
```

### 3.4 movement_order_event_log

Auditable event log for Movement Orders.

```sql
CREATE TABLE movement_order_event_log (
    EventID             BIGINT PRIMARY KEY AUTO_INCREMENT,
    MovementOrderID     BIGINT NOT NULL,
    MovementOrderLineID BIGINT NULL,
    EventType           VARCHAR(50) NOT NULL,
    EventDateTime       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PerformedBy         VARCHAR(100) NULL,
    Notes               TEXT NULL,

    FOREIGN KEY (MovementOrderID) REFERENCES movement_order_header(MovementOrderID),
    FOREIGN KEY (MovementOrderLineID) REFERENCES movement_order_line(MovementOrderLineID)
);

CREATE INDEX idx_moe_order ON movement_order_event_log (MovementOrderID);
CREATE INDEX idx_moe_line ON movement_order_event_log (MovementOrderLineID);
```

---

## 4. Integration with existing subsystems

### 4.1 Movement Orders and dispatch_action

- Movement Orders **never move equipment directly**.
- Each `movement_order_line` generates **one Dispatch** via existing dispatch creation logic.
- Integration path:

  ```text
  MovementOrderService.confirmMovementOrder()
      → DispatchService.createDispatchForMovementLine(line)
      → dispatch_action (Struts)
      → Dispatch status engine
  ```

- Dispatch types for Movement Orders:
  - Reposition / yard moves
  - Customer site moves
  - Swaps (two dispatches, one per line)

### 4.2 Movement Orders and equipment_events

- Dispatch completion already triggers equipment events.
- Movement Orders rely on the same event pipeline:

  ```text
  Dispatch completion
      → equipment_events
      → equipment_location_history insert
      → availability recalculation
      → current location view update
      → MovementOrderLine status update
      → MovementOrderHeader status recalculation
      → MovementOrderEventLog entry
  ```

- Movement Orders do **not** introduce a separate event engine; they reuse the existing one.

### 4.3 Movement Orders and location history

- Location history is written on **dispatch completion**, not on Movement Order creation.
- Movement Orders define the **intended target location**; dispatch completion records the actual location.

### 4.4 Movement Orders and availability engine

- Availability is derived from:
  - Reservations
  - Dispatches
  - Location history
  - Equipment status
- Movement Orders affect availability only through:
  - Dispatch creation
  - Dispatch status changes
  - Dispatch completion events

### 4.5 Movement Orders and reservations

- Movement Orders must not conflict with active reservations.
- Validation checks:
  - Equipment not in a pickup/delivery window
  - Equipment not assigned to a reservation dispatch
  - Equipment not in a reservation‑locked status
- Movement Orders do **not** modify reservations.

---

## 5. Status engine

### 5.1 Line status transitions

Typical transitions for `movement_order_line.LineStatus`:

- `NEW` → `ASSIGNED` (dispatch created/assigned)
- `ASSIGNED` → `IN_TRANSIT` (driver starts)
- `IN_TRANSIT` → `COMPLETED` (dispatch completed successfully)
- `IN_TRANSIT` → `FAILED` (dispatch failed)
- Any non‑terminal → `CANCELLED` (manual cancellation)

### 5.2 Header status derivation

`movement_order_header.Status` is derived from line statuses:

- If **any** line is `NEW` → header = `NEW`
- Else if **any** line is `IN_TRANSIT` or `ASSIGNED` → header = `IN_PROGRESS`
- Else if **all** lines are `COMPLETED` → header = `COMPLETED`
- Else if **all** lines are `CANCELLED` → header = `CANCELLED`
- Mixed `COMPLETED`/`FAILED` can be treated as `IN_PROGRESS` or a future `PARTIAL` state if desired.

Implementation sketch:

```java
Status deriveHeaderStatus(List<LineStatus> lineStatuses) {
    boolean anyNew = false;
    boolean anyActive = false; // ASSIGNED or IN_TRANSIT
    boolean anyCompleted = false;
    boolean anyCancelled = false;

    for (LineStatus s : lineStatuses) {
        switch (s) {
            case NEW: anyNew = true; break;
            case ASSIGNED:
            case IN_TRANSIT: anyActive = true; break;
            case COMPLETED: anyCompleted = true; break;
            case CANCELLED: anyCancelled = true; break;
            default: break;
        }
    }

    if (anyNew) return Status.NEW;
    if (anyActive) return Status.IN_PROGRESS;
    if (anyCompleted && !anyNew && !anyActive) return Status.COMPLETED;
    if (anyCancelled && !anyNew && !anyActive && !anyCompleted) return Status.CANCELLED;

    return Status.IN_PROGRESS; // fallback for mixed/edge cases
}
```

---

## 6. Validation rules

Before creating or confirming a Movement Order:

- **Equipment existence**
  - EquipmentNumber must exist in `equipment`.
- **No active movement conflict**
  - Equipment not already in a Movement Order line with active status (`NEW`, `ASSIGNED`, `IN_TRANSIT`).
- **No conflicting dispatch**
  - Equipment not assigned to another active dispatch that conflicts in time or purpose.
- **No reservation conflict**
  - Equipment not in a pickup/delivery window.
  - Equipment not in a reservation‑locked status.
- **Equipment status**
  - Must be in a state that allows movement (e.g., not hard‑locked for maintenance unless movement type is maintenance‑related).
- **Target location validity**
  - If `TargetLocationType = ON_PREMISE`, `TargetYardID` must be valid.
  - If `TargetLocationType = CUSTOMER_SITE`, address fields must be present/valid.

---

## 7. DAO layer

DAO interfaces (JDBC‑based, no ORM).

### 7.1 MovementOrderHeaderDAO

- `long insertHeader(MovementOrderHeader header)`
- `void updateHeaderStatus(long movementOrderId, Status status)`
- `MovementOrderHeader getHeaderById(long movementOrderId)`
- `List<MovementOrderHeader> listHeaders(MovementOrderFilter filter)`

### 7.2 MovementOrderLineDAO

- `long insertLine(MovementOrderLine line)`
- `void updateLineStatus(long lineId, LineStatus status)`
- `List<MovementOrderLine> getLinesByOrderId(long movementOrderId)`
- `MovementOrderLine getLineById(long lineId)`

### 7.3 MovementOrderSwapLinkDAO

- `void insertSwapLink(MovementOrderSwapLink link)`
- `MovementOrderSwapLink getSwapLinkByOrderId(long movementOrderId)`

### 7.4 MovementOrderEventLogDAO

- `void insertEvent(MovementOrderEvent event)`
- `List<MovementOrderEvent> listEventsByOrderId(long movementOrderId)`

---

## 8. Service layer

### 8.1 MovementOrderService

Core responsibilities:

- Create Movement Orders (header + lines)
- Validate Movement Orders
- Generate dispatches for lines
- Handle line completion/failure/cancellation
- Recalculate header status
- Write event log entries

Key methods (sketch):

```java
public class MovementOrderService {

    public long createMovementOrder(MovementOrderHeader header,
                                    List<MovementOrderLine> lines);

    public void confirmMovementOrder(long movementOrderId);

    public void handleDispatchCompletion(long dispatchId);

    public void cancelMovementOrder(long movementOrderId, String reason, String user);

    public void failMovementOrderLine(long lineId, String reason, String user);

    // internal helpers
    private void recalcHeaderStatus(long movementOrderId);
    private void logEvent(...);
}
```

### 8.2 DispatchService integration

- `DispatchService.createDispatchForMovementLine(MovementOrderLine line, MovementOrderHeader header)`
- Uses existing dispatch creation logic:
  - Pickup location = current equipment location
  - Dropoff location = target location from Movement Order line
  - Dispatch type = movement/reposition

### 8.3 Location / availability services

- On dispatch completion:
  - Write location history
  - Update equipment status
  - Recalculate availability
  - Update Movement Order line/header

---

## 9. Struts 6.x actions

All actions are XML‑configured (no annotations), under namespace `/movement`.

Example `struts.xml` package:

```xml
<package name="movement" namespace="/movement" extends="struts-default">

    <action name="list" class="com.yourapp.movement.ListMovementOrdersAction">
        <result name="success" type="tiles">movement.list</result>
    </action>

    <action name="create" class="com.yourapp.movement.CreateMovementOrderAction">
        <result name="input" type="tiles">movement.create</result>
        <result name="success" type="redirect">review?orderId=${orderId}</result>
    </action>

    <action name="review" class="com.yourapp.movement.ReviewMovementOrderAction">
        <result name="success" type="tiles">movement.review</result>
    </action>

    <action name="confirm" class="com.yourapp.movement.ConfirmMovementOrderAction">
        <result name="success" type="redirect">detail?orderId=${orderId}</result>
    </action>

    <action name="detail" class="com.yourapp.movement.ViewMovementOrderAction">
        <result name="success" type="tiles">movement.detail</result>
    </action>

    <action name="swapDetail" class="com.yourapp.movement.ViewSwapMovementOrderAction">
        <result name="success" type="tiles">movement.swapDetail</result>
    </action>

    <action name="bulkDetail" class="com.yourapp.movement.ViewBulkMovementOrderAction">
        <result name="success" type="tiles">movement.bulkDetail</result>
    </action>

</package>
```

Typical actions:

- `ListMovementOrdersAction`
- `CreateMovementOrderAction`
- `ReviewMovementOrderAction`
- `ConfirmMovementOrderAction`
- `ViewMovementOrderAction`
- `ViewSwapMovementOrderAction`
- `ViewBulkMovementOrderAction`
- `UpdateMovementOrderStatusAction`
- `AddEquipmentToBulkOrderAction`
- `RemoveEquipmentFromBulkOrderAction`

---

## 10. Tiles definitions

Example Tiles definitions:

```xml
<definition name="movement.list" extends="baseLayout">
    <put-attribute name="title" value="Movement Orders"/>
    <put-attribute name="body" value="/WEB-INF/jsp/movement/list.jsp"/>
</definition>

<definition name="movement.create" extends="baseLayout">
    <put-attribute name="title" value="Create Movement Order"/>
    <put-attribute name="body" value="/WEB-INF/jsp/movement/create.jsp"/>
</definition>

<definition name="movement.review" extends="baseLayout">
    <put-attribute name="title" value="Review Movement Order"/>
    <put-attribute name="body" value="/WEB-INF/jsp/movement/review.jsp"/>
</definition>

<definition name="movement.detail" extends="baseLayout">
    <put-attribute name="title" value="Movement Order Details"/>
    <put-attribute name="body" value="/WEB-INF/jsp/movement/detail.jsp"/>
</definition>

<definition name="movement.swapDetail" extends="baseLayout">
    <put-attribute name="title" value="Swap Movement Order"/>
    <put-attribute name="body" value="/WEB-INF/jsp/movement/swapDetail.jsp"/>
</definition>

<definition name="movement.bulkDetail" extends="baseLayout">
    <put-attribute name="title" value="Bulk Movement Order"/>
    <put-attribute name="body" value="/WEB-INF/jsp/movement/bulkDetail.jsp"/>
</definition>
```

---

## 11. UI architecture (Bootstrap 4.6.2 + JSP)

Key points:

- Use Bootstrap 4.6.2 syntax:
  - `data-toggle="modal"`, `data-target="#modalId"`
  - `.close` buttons for modals
  - jQuery‑driven components
- Screens:
  - Movement Order List
  - Movement Order Create
  - Movement Order Review
  - Movement Order Detail
  - Swap Movement Detail
  - Bulk Movement Detail
- Tables can use DataTables where appropriate.
- Forms use standard BS4 form layout (`.form-group`, `.form-control`, etc.).

---

## 12. Workflow summary

High‑level end‑to‑end flow:

```text
User creates Movement Order (header + lines)
    ↓
CreateMovementOrderAction → MovementOrderService.createMovementOrder()
    ↓
ReviewMovementOrderAction shows summary
    ↓
ConfirmMovementOrderAction → MovementOrderService.confirmMovementOrder()
    ↓
For each line: DispatchService.createDispatchForMovementLine()
    ↓
dispatch_action manages dispatch lifecycle
    ↓
Dispatch completion → equipment_events pipeline
    ↓
equipment_location_history insert
equipment availability recalculation
equipment current location update
MovementOrderLine status update
MovementOrderHeader status recalculation
MovementOrderEventLog entry
```

This file is intended to be the **canonical reference** for Movement Orders in your repo.  
You can now commit it as `movement-order-architecture.md` and tell GitHub Copilot Chat to use it as context when generating code.
```
