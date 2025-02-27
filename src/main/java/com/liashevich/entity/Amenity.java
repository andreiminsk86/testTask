package com.liashevich.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String description;

    @ManyToMany
    @JoinTable(name = "hotel_amenity", inverseJoinColumns = @JoinColumn(name = "hotel_id"))
    private List<Hotel> hotels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
