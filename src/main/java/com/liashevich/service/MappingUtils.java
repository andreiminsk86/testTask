package com.liashevich.service;

import com.liashevich.dto.HotelDTO;
import com.liashevich.dto.HotelDTOExtended;
import com.liashevich.entity.*;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    private static final int DESCRIPTION_LIMIT = 150;

    public HotelDTO mapToHotelDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setDescription(shorterString(hotel.getDescription()));
        hotelDTO.setAddress(addressToString(hotel.getAddress()));
        hotelDTO.setPhone(hotel.getContacts().getPhone());
        return hotelDTO;
    }

    private String addressToString(Address address){
        StringBuilder builder = new StringBuilder();
        builder.append(address.getHouseNumber())
                .append(" ")
                .append(address.getStreet())
                .append(", ")
                .append(address.getCity())
                .append(", ")
                .append(address.getPostCode())
                .append(", ")
                .append(address.getCountry());
        return builder.toString();
    }

    public HotelDTOExtended mapToHotelDTOExtended(Hotel hotel) {
        HotelDTOExtended hotelDTOExtended = new HotelDTOExtended();
        hotelDTOExtended.setId(hotel.getId());
        hotelDTOExtended.setName(hotel.getName());
        hotelDTOExtended.setDescription(shorterString(hotel.getDescription()));
        hotelDTOExtended.setBrand(hotel.getBrand());
        hotelDTOExtended.setAddress(hotel.getAddress());
        hotelDTOExtended.setContacts(hotel.getContacts());
        hotelDTOExtended.setArrivalTime(hotel.getArrivalTime());
        hotelDTOExtended.setAmenities(hotel.getAmenities().stream().map(Amenity::getDescription).toList());
        return hotelDTOExtended;
    }

    private String shorterString(String description) {
        if(description == null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        if (description.length() <= DESCRIPTION_LIMIT) {
            return description;
        } else {
            int index = description.indexOf(" ", DESCRIPTION_LIMIT);
            if (index == -1) {
                stringBuilder.append(description, 0, DESCRIPTION_LIMIT).append(" ...");
            } else {
                stringBuilder.append(description, 0, index).append((" ..."));
            }


        }
        return stringBuilder.toString();
    }



}
