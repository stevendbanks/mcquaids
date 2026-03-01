package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setUserID(rs.getString("UserID"));
        customer.setFirstName(rs.getString("FirstName"));
        customer.setLastName(rs.getString("LastName"));
        customer.setBusinessName(rs.getString("BusinessName"));   // NEW
        customer.setPhone(rs.getString("Phone"));
        customer.setEmail(rs.getString("Email"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("City"));
        customer.setProvince(rs.getString("Province"));
        customer.setCountry(rs.getString("Country"));
        customer.setPostalCode(rs.getString("PostalCode"));
        customer.setNotes(rs.getString("Notes"));
        customer.setCreatedDateTime(rs.getTimestamp("CreatedDateTime"));
        customer.setCreatedUserID(rs.getString("CreatedUserID"));
        return customer;
    }
}
