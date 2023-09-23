package com.example.graphtutorial.repository;

import com.example.graphtutorial.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {

    Mono<Car> findCarByUuid(String uuid);

}
