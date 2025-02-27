package com.liashevich.service;

import com.liashevich.dto.HotelCounterByParamDTO;
import com.liashevich.dto.HotelDTO;
import com.liashevich.dto.HotelDTOExtended;
import com.liashevich.entity.Amenity;
import com.liashevich.entity.Hotel;
import com.liashevich.exception.HotelNotFoundException;
import com.liashevich.exception.RequiredFieldMissingException;
import com.liashevich.repository.AmenityRepository;
import com.liashevich.repository.HotelRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    private MappingUtils mappingUtils;

    public HotelDTOExtended getById(int id) {
        return mappingUtils.mapToHotelDTOExtended(hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new));
    }

    public HotelDTO saveOrUpdate(Hotel hotel) {
        try {
            hotelRepository.save(hotel);
        } catch (ConstraintViolationException ex) {
            List<String> list = ex.getConstraintViolations().stream().map(constraintViolation -> {
                return constraintViolation.getPropertyPath().toString();
            }).toList();

            throw new RequiredFieldMissingException(list);
        }

        return mappingUtils.mapToHotelDTO(hotel);
    }

    public List<HotelDTO> getAll() {
        List<HotelDTO> hotels = new ArrayList<>();
        hotelRepository.findAll().forEach(hotel -> hotels.add(mappingUtils.mapToHotelDTO(hotel)));
        return hotels;
    }

    public void addAmenities(int id, List<String> amenities) {


        Hotel hotel = hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);


        List<Amenity> amenityList = amenities.stream().map(description -> {
            Amenity amenity = amenityRepository.findByDescription(description);
            if (amenity == null) {
                amenity = new Amenity();
                amenity.setDescription(description);
            }

            return amenity;

        }).toList();


        hotel.getAmenities().addAll(amenityList);
        hotelRepository.save(hotel);
    }

    public List<HotelDTO> searchByParam(Map<String, String> params) {
        Set<String> keys = params.keySet();
        List<Hotel> hotels = List.of();

        if (keys.contains("name")) {
            String value = params.get("name");
            hotels = hotelRepository.findByNameIgnoreCase(value);
        } else if (keys.contains("brand")) {
            String value = params.get("brand");
            hotels = hotelRepository.findByBrandIgnoreCase(value);
        } else if (keys.contains("city")) {
            String value = params.get("city");
            hotels = hotelRepository.findByAddressCityIgnoreCase(value);
        } else if (keys.contains("country")) {
            String value = params.get("country");
            hotels = hotelRepository.findByAddressCountryIgnoreCase(value);
        } else if (keys.contains("amenities")) {
            String value = params.get("amenities");
            hotels = hotelRepository.findByAmenitiesDescriptionIgnoreCase(value);
        }

        return hotels.stream().map(hotel -> mappingUtils.mapToHotelDTO(hotel)).toList();

    }

    public Map<String, Long> countAndGroupByParam(String param) {

        Map<String, Long> response = new HashMap<>();
        List<HotelCounterByParamDTO> counterByParamDTOS = List.of();

        if (param.equals("brand")) {
            counterByParamDTOS = hotelRepository.getGroupedByBrand();
        } else if (param.equals("city")) {
            counterByParamDTOS = hotelRepository.getGroupedByCity();
        } else if (param.equals("country")) {
            counterByParamDTOS = hotelRepository.getGroupedByCountry();
        } else if (param.equals("amenities")) {
            counterByParamDTOS = hotelRepository.getGroupedByAmenity();
            hotelRepository.getGroupedByAmenity().stream().forEach(value -> {
                response.put(value.getParam(), value.getCounter());
            });
        }

        counterByParamDTOS.forEach(value -> response.put(value.getParam(), value.getCounter()));

        return response;
    }

}


