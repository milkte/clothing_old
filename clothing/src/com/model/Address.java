package com.model;

/**
 * Created by SRawla on 4/10/2015.
 */
public class Address {

    private int addressId;
    private String street;
    private String state;
    private String city;
    private int zipcode;

    public Address() {
    }

    public Address(int addressId, String Street, String State, String City, int Zipcode) {
        this.addressId = addressId;
        this.street = Street;
        this.state = State;
        this.city = City;
        this.zipcode = Zipcode;
    }

    /**
     * @return the addressId
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param Street the street to set
     */
    public void setStreet(String Street) {
        this.street = Street;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param State the state to set
     */
    public void setState(String State) {
        this.state = State;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param City the city to set
     */
    public void setCity(String City) {
        this.city = City;
    }

    /**
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * @param Zipcode the zipcode to set
     */
    public void setZipcode(int Zipcode) {
        this.zipcode = Zipcode;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", Street=" + street + ", State=" + state + ", City=" + city + ", Zipcode=" + zipcode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.addressId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        return this.addressId == other.getAddressId();
    }

}
