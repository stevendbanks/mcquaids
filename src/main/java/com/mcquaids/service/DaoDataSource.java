package com.mcquaids.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DaoDataSource {
    protected static DriverManagerDataSource dataSource;
    protected static JdbcTemplate jdbcTemplate;

    public static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mcquaids_trailers");
        dataSource.setUsername("mcquaids");
        dataSource.setPassword("mcquaids");

        jdbcTemplate = new JdbcTemplate(dataSource);

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        
        
    }

    public DaoDataSource() {
        super();
    }
}
