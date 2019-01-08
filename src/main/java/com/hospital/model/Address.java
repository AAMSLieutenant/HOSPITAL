package com.hospital.model;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private int houseNumber;
    private int flatNumber;

    public Address(){

    }

    public Address(String city, String street, int houseNumber, int flatNumber){
        this.city=city;
        this.street=street;
        this.houseNumber=houseNumber;
        this.flatNumber=flatNumber;

    }

    public Address(Address a){
        this.city=a.city;
        this.street=a.street;
        this.houseNumber=a.houseNumber;
        this.flatNumber=a.flatNumber;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", flatNumber=" + flatNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getHouseNumber() == address.getHouseNumber() &&
                getFlatNumber() == address.getFlatNumber() &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getHouseNumber(), getFlatNumber());
    }
}
