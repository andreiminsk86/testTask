package com.liashevich.controller;

import com.liashevich.entity.Hotel;
import com.liashevich.dto.HotelDTO;
import com.liashevich.dto.HotelDTOExtended;
import com.liashevich.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/property-view")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @GetMapping("/hotels/{id}")
    public HotelDTOExtended getHotel(@PathVariable("id") int id) {
        return hotelService.getById(id);
    }

    @PostMapping("/hotels")
    public HotelDTO createHotel(@RequestBody Hotel hotel) {
        return hotelService.saveOrUpdate(hotel);
    }

    @GetMapping("/hotels")
    public List<HotelDTO> getAll() {
        return hotelService.getAll();
    }

    @PostMapping("/hotels/{id}/amenities")
    public void addAmenitiesToHotel(@PathVariable("id") int id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }

    @GetMapping("/search")
    public List<HotelDTO> search(@RequestParam Map<String, String> params) {
        return hotelService.searchByParam(params);
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> groupByParam(@PathVariable("param") String param) {
        return hotelService.countAndGroupByParam(param);
    }

}
