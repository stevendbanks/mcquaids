package com.mcquaids.dao;

import com.mcquaids.model.Address;
import com.mcquaids.model.Yard;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class YardDAO {

    private final JdbcTemplate jdbcTemplate;

    public YardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Yard> findAll() {
        String sql = "SELECT * FROM yard";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Yard yard = new Yard();
            yard.setYardId(rs.getLong("yard_id"));
            yard.setName(rs.getString("name"));

            Address addr = new Address(
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("postal"),
                    rs.getString("country")
            );
            yard.setAddress(addr);

            yard.setDefaultYard(rs.getBoolean("is_default"));
            return yard;
        });
    }

    public Yard findDefaultYard() {
        String sql = "SELECT * FROM yard WHERE is_default = 1 LIMIT 1";

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                Yard yard = new Yard();
                yard.setYardId(rs.getLong("yard_id"));
                yard.setName(rs.getString("name"));

                Address addr = new Address(
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal"),
                        rs.getString("country")
                );
                yard.setAddress(addr);

                yard.setDefaultYard(true);
                return yard;
            }
            return null;
        });
    }
}