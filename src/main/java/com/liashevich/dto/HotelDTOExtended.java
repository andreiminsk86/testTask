package com.liashevich.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.liashevich.entity.Address;
import com.liashevich.entity.Amenity;
import com.liashevich.entity.ArrivalTime;
import com.liashevich.entity.Contacts;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTOExtended {




    private int id;

    private String name;

    private String description;


    private String brand;

    private Address address;


    private Contacts contacts;


    private ArrivalTime arrivalTime;


    private List<String> amenities = new ArrayList<>();

    public HotelDTOExtended(int id, String name, String description, String brand, Address address, Contacts contacts, ArrivalTime arrivalTime, List<Amenity> amenities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.address = address;
        this.contacts = contacts;
        this.arrivalTime = arrivalTime;
        amenities.forEach(amenity -> this.amenities.add(amenity.getDescription()));
    }



    public HotelDTOExtended() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public ArrivalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrivalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}
