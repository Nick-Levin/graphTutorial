package com.example.graphtutorial.controller;

import com.example.graphtutorial.model.Car;
import com.example.graphtutorial.model.Person;
import com.example.graphtutorial.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @MutationMapping
    public Mono<Car> createCar(@Argument Car car) {
        return carRepository.save(car);
    }

    @QueryMapping
    public Flux<Car> getAllCars() {
        return carRepository.findAll();
    }

}
