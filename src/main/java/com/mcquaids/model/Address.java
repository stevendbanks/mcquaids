package com.mcquaids.model;

public class Address {

    private String street;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Address() {}

    public Address(String street, String city, String province, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
 
    
    public String toSingleLine() {
        StringBuilder sb = new StringBuilder();

        if (street != null && !street.isBlank()) sb.append(street);

        if (city != null && !city.isBlank()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(city);
        }

        if (province != null && !province.isBlank()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(province);
        }

//        if (postalCode != null && !postalCode.isBlank()) {
//            sb.append(" ").append(postalCode);
//        }
//
//        if (country != null && !country.isBlank()) {
//            if (sb.length() > 0) sb.append(", ");
//            sb.append(country);
//        }

        return sb.toString();
    }    
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address other = (Address) o;

        return safe(street).equalsIgnoreCase(safe(other.street)) &&
               safe(city).equalsIgnoreCase(safe(other.city)) &&
               safe(province).equalsIgnoreCase(safe(other.province)) &&
               safe(postalCode).equalsIgnoreCase(safe(other.postalCode)) &&
               safe(country).equalsIgnoreCase(safe(other.country));
    }

    @Override
    public int hashCode() {
        return (safe(street) + "|" +
                safe(city) + "|" +
                safe(province) + "|" +
                safe(postalCode) + "|" +
                safe(country))
                .toLowerCase()
                .hashCode();
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }    
    
}