package com.mcquaids.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mcquaids.model.Yard;
import com.mcquaids.model.Address;

public class YardRowMapper implements RowMapper<Yard> {

    @Override
    public Yard mapRow(ResultSet rs, int rowNum) throws SQLException {

        Yard yard = new Yard();
        yard.setYardId(rs.getLong("yard_id"));
        yard.setName(rs.getString("name"));
        yard.setDefaultYard(rs.getBoolean("is_default"));

        // Build Address object from yard table columns
        Address addr = new Address();
        addr.setStreet(rs.getString("street"));
        addr.setCity(rs.getString("city"));
        addr.setProvince(rs.getString("province"));
        addr.setPostalCode(rs.getString("postal"));
        addr.setCountry(rs.getString("country"));

        yard.setAddress(addr);

        return yard;
    }
}