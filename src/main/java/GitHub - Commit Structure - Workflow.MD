# Commit Workflow Guide

This document defines the standard workflow for creating feature branches, committing changes, and maintaining a clean, traceable Git history for this project.

---

## 1. Start From a Clean `main` Branch

Before beginning any new work:

1. Open Sourcetree  
2. Checkout the `main` branch  
3. Pull the latest changes from GitHub  
4. Confirm previous feature branches have been merged and deleted (remote + local)

---

## 2. Create a GitHub Issue

All new features or fixes must begin with a GitHub Issue.

Each issue should include:
- A clear title  
- A description of the feature or fix  
- Goals and acceptance criteria  

**Example:**  
_Add Delivery Location Support to Reservation Workflow (#4)_

---

## 3. Create a Feature Branch

Create a new branch from `main` using the following naming pattern:

```
feature/issue-XX-description
```

**Example:**

```
feature/issue-04-delivery-location
```

This ensures traceability between Issues → Branches → Pull Requests.

---

## 4. Commit Structure for Feature Development

Commits must be **small, atomic, and logically grouped**.  
Each commit message should reference the issue number.

Below is the recommended commit sequence for feature development.

---

### Commit 1 — Add Model Changes

Introduce new fields or data structures required for the feature.

**Typical changes:**
- Update domain model  
- Update DTOs / form beans  
- Add getters/setters  
- Update serialization if needed  

**Commit message:**
```
Add deliveryLocation field to Reservation model (#4)
```

---

### Commit 2 — Update Workflow and Backend Logic

Ensure the new data flows through the reservation workflow.

**Typical changes:**
- Update Struts actions  
- Update workflow context  
- Update JSON handlers  
- Ensure AJAX and full-screen flows support the new field  

**Commit message:**
```
Update reservation workflow to support deliveryLocation (#4)
```

---

### Commit 3 — Add UI Input Field

Expose the new field in the user interface.

**Typical changes:**
- Add input field to JSP  
- Apply consistent layout and styling  
- Add client-side validation if applicable  

**Commit message:**
```
Add deliveryLocation input field to reservation UI (#4)
```

---

### Commit 4 — Add Server-Side Validation

Ensure the new field meets business rules.

**Typical changes:**
- Add validation rules  
- Add error messages  
- Ensure consistent behavior across AJAX and full-screen flows  

**Commit message:**
```
Add validation rules for deliveryLocation (#4)
```

---

### Commit 5 — Update Summary and Confirmation Views

Display the new field wherever reservation details appear.

**Typical changes:**
- Update summary JSP  
- Update confirmation page  
- Update list/detail views  

**Commit message:**
```
Display deliveryLocation in reservation summary and confirmation views (#4)
```

---

### Commit 6 — Add Tests and Cleanup

Finalize the feature with tests and code cleanup.

**Typical changes:**
- Add unit tests  
- Add integration tests  
- Remove dead code  
- Ensure formatting consistency  

**Commit message:**
```
Add tests and perform final cleanup for deliveryLocation feature (#4)
```

---

## 5. Push and Open a Pull Request

Once development is complete:

1. Push the feature branch to GitHub  
2. Open a Pull Request  
3. Link it to the issue using:  
   ```
   Closes #4
   ```
4. Merge into `main` after review  
5. Delete the branch (remote + local)

---
