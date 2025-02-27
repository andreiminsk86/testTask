package com.liashevich.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNullFields;

import java.util.Set;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column
    @NotNull
    private String name;

    @Column(columnDefinition = "text")
    @Lob
    private String description;

    @NotNull
    @Column
    private String brand;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private Contacts contacts;

    @OneToOne(cascade = CascadeType.ALL)
    private ArrivalTime arrivalTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_amenity", inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;


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

    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
    }
}
