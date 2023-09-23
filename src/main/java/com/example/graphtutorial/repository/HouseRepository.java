package com.example.graphtutorial.repository;

import com.example.graphtutorial.model.House;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HouseRepository extends ReactiveMongoRepository<House, String> {

}
