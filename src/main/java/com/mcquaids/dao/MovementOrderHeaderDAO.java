package com.mcquaids.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mcquaids.model.MovementOrderDTO;
import com.mcquaids.model.MovementOrderHeader;
import com.mcquaids.model.MovementOrderHeader.MovementType;
import com.mcquaids.model.MovementOrderHeader.Priority;
import com.mcquaids.model.MovementOrderHeader.TargetLocationType;
import com.mcquaids.model.enums.MovementOrderStatus;

public class MovementOrderHeaderDAO {

    private final NamedParameterJdbcTemplate template;

    public MovementOrderHeaderDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    // ------------------------------------------------------------
    // SQL
    // ------------------------------------------------------------

    private static final String INSERT_SQL =
        "INSERT INTO movement_order_header (" +
        "RequestedBy, RequestedDateTime, Priority, MovementType, ReasonCode, Notes, Status, " +
        "TargetLocationType, TargetYardID, TargetStreet, TargetCity, TargetProvince, TargetPostal, TargetCountry" +
        ") VALUES (" +
        ":RequestedBy, :RequestedDateTime, :Priority, :MovementType, :ReasonCode, :Notes, :Status, " +
        ":TargetLocationType, :TargetYardID, :TargetStreet, :TargetCity, :TargetProvince, :TargetPostal, :TargetCountry" +
        ")";

    private static final String UPDATE_STATUS_SQL =
        "UPDATE movement_order_header SET Status = :Status WHERE MovementOrderID = :MovementOrderID";

    private static final String SELECT_BY_ID_SQL =
        "SELECT * FROM movement_order_header WHERE MovementOrderID = :MovementOrderID";

    private static final String SELECT_ALL_SQL =
        "SELECT * FROM movement_order_header ORDER BY MovementOrderID DESC";

    // ------------------------------------------------------------
    // INSERT (KeyHolder)
    // ------------------------------------------------------------

    public long insertHeader(MovementOrderHeader header) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("RequestedBy", header.getRequestedBy())
                .addValue("RequestedDateTime", header.getRequestedDate())
                .addValue("Priority", header.getPriority() != null ? header.getPriority().name() : null)
                .addValue("MovementType", header.getMovementType() != null ? header.getMovementType().name() : null)
                .addValue("ReasonCode", header.getReasonCode())
                .addValue("Notes", header.getNotes())
                .addValue("Status", header.getStatus() != null ? header.getStatus().name() : null)

                // Destination fields
                .addValue("TargetLocationType", header.getTargetLocationType() != null ? header.getTargetLocationType().name() : null)
                .addValue("TargetYardID", header.getTargetYardId())
                .addValue("TargetStreet", header.getTargetStreet())
                .addValue("TargetCity", header.getTargetCity())
                .addValue("TargetProvince", header.getTargetProvince())
                .addValue("TargetPostal", header.getTargetPostal())
                .addValue("TargetCountry", header.getTargetCountry());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(INSERT_SQL, params, keyHolder, new String[]{"MovementOrderID"});

        Number key = keyHolder.getKey();
        if (key != null) {
            long id = key.longValue();
            header.setMovementOrderId(id);
            return id;
        }

        return -1;
    }

    // ------------------------------------------------------------
    // UPDATE STATUS
    // ------------------------------------------------------------

    public void updateStatus(long movementOrderId, MovementOrderStatus status) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", movementOrderId)
                .addValue("Status", status.name());

        template.update(UPDATE_STATUS_SQL, params);
    }

    // ------------------------------------------------------------
    // SELECT BY ID
    // ------------------------------------------------------------

    public MovementOrderHeader getHeaderById(long movementOrderId) {

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("MovementOrderID", movementOrderId);

        List<MovementOrderHeader> list = template.query(
                SELECT_BY_ID_SQL,
                params,
                (rs, rowNum) -> mapRow(rs)
        );

        return list.isEmpty() ? null : list.get(0);
    }

    // ------------------------------------------------------------
    // SELECT ALL
    // ------------------------------------------------------------

    public List<MovementOrderHeader> listAllHeaders() {
        return template.query(
                SELECT_ALL_SQL,
                (rs, rowNum) -> mapRow(rs)
        );
    }

    // ------------------------------------------------------------
    // ROW MAPPER
    // ------------------------------------------------------------

    private MovementOrderHeader mapRow(ResultSet rs) throws SQLException {

        MovementOrderHeader h = new MovementOrderHeader();

        h.setMovementOrderId(rs.getLong("MovementOrderID"));
        h.setRequestedBy(rs.getString("RequestedBy"));

        LocalDateTime dt = rs.getTimestamp("RequestedDateTime") != null
                ? rs.getTimestamp("RequestedDateTime").toLocalDateTime()
                : null;
        h.setRequestedDate(dt);

        String priority = rs.getString("Priority");
        h.setPriority(priority != null ? Priority.valueOf(priority) : null);

        String movementType = rs.getString("MovementType");
        h.setMovementType(movementType != null ? MovementType.valueOf(movementType) : null);

        h.setReasonCode(rs.getString("ReasonCode"));
        h.setNotes(rs.getString("Notes"));

        String status = rs.getString("Status");
        h.setStatus(status != null ? MovementOrderStatus.valueOf(status) : null);

        // Destination fields
        String tgtType = rs.getString("TargetLocationType");
        h.setTargetLocationType(tgtType != null ? TargetLocationType.valueOf(tgtType) : null);

        h.setTargetYardId(rs.getObject("TargetYardID", Long.class));
        h.setTargetStreet(rs.getString("TargetStreet"));
        h.setTargetCity(rs.getString("TargetCity"));
        h.setTargetProvince(rs.getString("TargetProvince"));
        h.setTargetPostal(rs.getString("TargetPostal"));
        h.setTargetCountry(rs.getString("TargetCountry"));

        return h;
    }

    public List<MovementOrderDTO> search(
            String status,
            String movementType,
            Integer equipmentNumber,
            String driver
    ) {

        StringBuilder sql = new StringBuilder(
            "SELECT " +
            "   h.MovementOrderID, " +
            "   h.MovementType, " +
            "   h.Status, " +
            "   h.RequestedDateTime, " +
            "   h.RequestedBy, " +

            "   MIN(l.EquipmentNumber) AS EquipmentNumber, " +

            // FROM location (equipment current location)
            "   MIN(CONVERT(loc.City     USING utf8mb4)) AS FromCity, " +
            "   MIN(CONVERT(loc.Province USING utf8mb4)) AS FromProvince, " +

            // Equipment type/subtype from qryequipmentdetails
            "   MIN(CONVERT(qd.equipmentTypeText     USING utf8mb4)) AS EquipmentTypeText, " +
            "   MIN(CONVERT(qd.equipmentSubTypeText USING utf8mb4)) AS EquipmentSubTypeText, " +

            // TO location (yard override logic)
            "   CASE WHEN h.TargetYardID IS NOT NULL THEN y.name ELSE NULL END AS ToName, " +
            "   CASE WHEN h.TargetYardID IS NOT NULL THEN y.street ELSE h.TargetStreet END AS ToStreet, " +
            "   CASE WHEN h.TargetYardID IS NOT NULL THEN y.city ELSE h.TargetCity END AS ToCity, " +
            "   CASE WHEN h.TargetYardID IS NOT NULL THEN y.province ELSE h.TargetProvince END AS ToProvince " +

            "FROM movement_order_header h " +
            "LEFT JOIN movement_order_line l ON h.MovementOrderID = l.MovementOrderID " +
            "LEFT JOIN equipment_current_location_view loc ON loc.EquipmentNumber = l.EquipmentNumber " +
            "LEFT JOIN qryequipmentdetails qd ON qd.EquipmentNumber = l.EquipmentNumber " +
            "LEFT JOIN yard y ON y.yard_id = h.TargetYardID " +
            "WHERE 1=1 "
        );

        Map<String, Object> params = new HashMap<>();

        if (status != null && !status.isEmpty()) {
            sql.append(" AND h.Status = :status ");
            params.put("status", status);
        }

        if (movementType != null && !movementType.isEmpty()) {
            sql.append(" AND h.MovementType = :movementType ");
            params.put("movementType", movementType);
        }

        if (equipmentNumber != null) {
            sql.append(" AND l.EquipmentNumber = :equipmentNumber ");
            params.put("equipmentNumber", equipmentNumber);
        }

        if (driver != null && !driver.isEmpty()) {
            sql.append(" AND h.RequestedBy LIKE :driver ");
            params.put("driver", "%" + driver + "%");
        }

        sql.append(" GROUP BY h.MovementOrderID ");
        sql.append(" ORDER BY h.RequestedDateTime DESC ");

        return template.query(sql.toString(), params, (rs, rowNum) -> {

            MovementOrderDTO dto = new MovementOrderDTO();

            dto.setMovementOrderId(rs.getLong("MovementOrderID"));
            dto.setDisplayOrderNumber("MO-" + rs.getLong("MovementOrderID"));

            // Raw equipment fields
            dto.setEquipmentNumber(rs.getInt("EquipmentNumber"));
            dto.setEquipmentTypeText(rs.getString("EquipmentTypeText"));
            dto.setEquipmentSubTypeText(rs.getString("EquipmentSubTypeText"));

            // FROM location
            dto.setFromLocationDisplay(
                safeJoin(
                    rs.getString("FromCity"),
                    rs.getString("FromProvince")
                )
            );

            // TO location (yard or customer site)
            String toName = rs.getString("ToName");
            String toStreet = rs.getString("ToStreet");
            String toCity = rs.getString("ToCity");
            String toProvince = rs.getString("ToProvince");

            if (toName != null) {
                dto.setToLocationDisplay(
                    safeJoin(toName, toCity, toProvince)
                );
            } else {
                dto.setToLocationDisplay(
                    safeJoin(toStreet, toCity, toProvince)
                );
            }

            dto.setMovementTypeText(rs.getString("MovementType"));
            dto.setStatus(rs.getString("Status"));
            dto.setDriverName(rs.getString("RequestedBy"));

            Timestamp ts = rs.getTimestamp("RequestedDateTime");
            if (ts != null) {
                dto.setRequestedAtFormatted(
                    ts.toLocalDateTime().format(
                        DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a")
                    )
                );
            }

            return dto;
        });
    }

    private String safeJoin(String... parts) {
        return Arrays.stream(parts)
                .filter(p -> p != null && !p.trim().isEmpty())
                .collect(Collectors.joining(", "));
    }




}
