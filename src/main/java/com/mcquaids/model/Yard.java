package com.mcquaids.model;

public class Yard {

    private Long yardId;
    private String name;
    private Address address;
    private boolean defaultYard;

    public Long getYardId() { return yardId; }
    public void setYardId(Long yardId) { this.yardId = yardId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public boolean isDefaultYard() { return defaultYard; }
    public void setDefaultYard(boolean defaultYard) { this.defaultYard = defaultYard; }
}