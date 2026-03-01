package com.mcquaids.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mcquaids.dao.rowmappers.CustomerEquipmentRowMapper;
import com.mcquaids.model.CustomerEquipmentDTO;
import com.mcquaids.model.lookup.CodeValues;

public class CustomerDAO extends UserDAO {

	public CustomerDAO() {
		super();
	}
	
	public List<CustomerEquipmentDTO> getEquipmentForCustomer(String customerID) {

		String sql =
			    "SELECT " +
			    "   r.ReservationID, " +
			    "   r.CustomerID, " +
			    "   r.StartDate, " +
			    "   r.EndDate, " +
			    "   r.ReservationStatusCode, " +
			    "   rs.EnglishDescription AS ReservationStatusCodeText, " +   // NEW
			    "   li.reservationLineItem, " +
			    "   li.EquipmentNumber, " +
			    "   e.SpecialNotes AS EquipmentNotes, " +
			    "   e.EquipmentType, " +
			    "   e.EquipmentSubType, " +
			    "   et.EnglishDescription AS EquipmentTypeText, " +
			    "   CASE " +
			    "       WHEN e.EquipmentType = 2 THEN st.EnglishDescription " +
			    "       WHEN e.EquipmentType = 3 THEN fb.EnglishDescription " +
			    "       WHEN e.EquipmentType = 4 THEN ct.EnglishDescription " +
			    "       WHEN e.EquipmentType = 5 THEN fk.EnglishDescription " +
			    "       ELSE NULL " +
			    "   END AS EquipmentSubTypeText " +
			    "FROM reservation r " +
			    "INNER JOIN reservation_line_item li ON r.ReservationID = li.ReservationID " +
			    "INNER JOIN equipment e ON li.EquipmentNumber = e.EquipmentNumber " +
			    "LEFT JOIN lkp_equipmenttypes et ON e.EquipmentType = et.CodeValue " +
			    "LEFT JOIN lkp_trailersubtypes st ON e.EquipmentSubType = st.CodeValue " +
			    "LEFT JOIN lkp_flatbedsubtypes fb ON e.EquipmentSubType = fb.CodeValue " +
			    "LEFT JOIN lkp_containersubtypes ct ON e.EquipmentSubType = ct.CodeValue " +
			    "LEFT JOIN lkp_forkliftsubtypes fk ON e.EquipmentSubType = fk.CodeValue " +
			    "LEFT JOIN lkp_reservationstatus rs ON r.ReservationStatusCode = rs.CodeValue " + // NEW
			    "WHERE r.CustomerID = :customerID " +
			    "ORDER BY r.ReservationID, li.EquipmentNumber";
		
		System.out.println(sql);

	    MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("customerID", customerID);

	    NamedParameterJdbcTemplate named =
	            new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

	    return named.query(sql, params, new CustomerEquipmentRowMapper());
	}	

}
