package com.liashevich.repository;

import com.liashevich.entity.Hotel;
import com.liashevich.dto.HotelCounterByParamDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    List<Hotel> findByNameIgnoreCase(String name);
    List<Hotel> findByBrandIgnoreCase(String brand);
    List<Hotel> findByAddressCityIgnoreCase(String city);
    List<Hotel> findByAddressCountryIgnoreCase(String city);
    List<Hotel> findByAmenitiesDescriptionIgnoreCase(String description);


    @Query(value = "select brand, count(*) from hotel " +
            "group by brand", nativeQuery = true)
    List<HotelCounterByParamDTO> getGroupedByBrand();

    @Query(value = "select address.city, count(*) from hotel " +
            "join address on hotel.address_id = address.id " +
            "group by address.city", nativeQuery = true)
    List<HotelCounterByParamDTO> getGroupedByCity();

    @Query(value = "select address.country, count(*) from hotel " +
            "join address on hotel.address_id = address.id " +
            "group by address.country", nativeQuery = true)
    List<HotelCounterByParamDTO> getGroupedByCountry();

    @Query(value = "select amenity.description, count(*) from amenity " +
            "join hotel_amenity on amenity.id = hotel_amenity.amenity_id " +
            "group by amenity.description", nativeQuery = true)
    List<HotelCounterByParamDTO> getGroupedByAmenity();



}
