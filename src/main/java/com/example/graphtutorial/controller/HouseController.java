package com.example.graphtutorial.controller;

import com.example.graphtutorial.model.House;
import com.example.graphtutorial.model.Person;
import com.example.graphtutorial.repository.HouseRepository;
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
public class HouseController {

    private final HouseRepository houseRepository;

    @MutationMapping
    public Mono<House> createHouse(@Argument House house) {
        return houseRepository.save(house);
    }

    @QueryMapping
    public Flux<House> getAllHouses() {
        return houseRepository.findAll();
    }

}
