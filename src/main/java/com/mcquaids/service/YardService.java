package com.mcquaids.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mcquaids.dao.YardDAO;
import com.mcquaids.model.Address;
import com.mcquaids.model.Yard;

public class YardService {

    private final YardDAO yardDAO;
    private List<Yard> cachedYards;

    public YardService() {
        JdbcTemplate jdbcTemplate = DaoDataSource.jdbcTemplate;
        this.yardDAO = new YardDAO(jdbcTemplate);
        this.cachedYards = yardDAO.findAll();
    }

    public boolean isYardAddress(Address address) {
        if (address == null) return false;

        for (Yard yard : cachedYards) {
            Address y = yard.getAddress();
            if (y.getStreet().equalsIgnoreCase(address.getStreet()) &&
                y.getCity().equalsIgnoreCase(address.getCity()) &&
                y.getProvince().equalsIgnoreCase(address.getProvince()) &&
                y.getPostalCode().equalsIgnoreCase(address.getPostalCode()) &&
                y.getCountry().equalsIgnoreCase(address.getCountry())) {
                return true;
            }
        }
        return false;
    }

    public Address getPreferredYardAddress() {
        for (Yard yard : cachedYards) {
            if (yard.isDefaultYard()) {
                return yard.getAddress();
            }
        }

        // fallback: first yard in list
        return cachedYards.isEmpty() ? null : cachedYards.get(0).getAddress();
    }
}