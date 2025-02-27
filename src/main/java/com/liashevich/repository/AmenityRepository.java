package com.liashevich.repository;

import com.liashevich.entity.Amenity;
import com.liashevich.entity.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AmenityRepository extends CrudRepository<Amenity, Integer> {


    Amenity findByDescription(String description);
}
