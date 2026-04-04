package com.mcquaids.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.model.EquipmentEvent;

public class EquipmentEventDAO {

    private final JdbcTemplate jdbcTemplate;

    public EquipmentEventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(EquipmentEvent event) {

        String sql =
            "INSERT INTO equipment_events " +
            "(equipment_number, event_type, action_id, action_type, notes, created_at, created_by) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
            event.getEquipmentNumber(),
            event.getEventType(),
            event.getActionId(),     // may be null
            event.getActionType(),   // may be null
            event.getNotes(),
            event.getCreatedAt(),    // LocalDateTime
            event.getCreatedBy()     // may be null
        );
    }
}