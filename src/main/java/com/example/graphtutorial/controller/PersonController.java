package com.example.graphtutorial.controller;

import com.example.graphtutorial.exception.NotFoundException;
import com.example.graphtutorial.model.Person;
import com.example.graphtutorial.repository.CarRepository;
import com.example.graphtutorial.repository.PersonRepository;
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
public class PersonController {

    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    @MutationMapping
    public Mono<Person> createPerson(@Argument Person person) {
        return personRepository.save(person);
    }

    @MutationMapping
    public Mono<Person> addCarToPerson(
            @Argument String carUuid,
            @Argument String personUuid
    ) {
        return Mono.zip(
                personRepository.findPersonByUuid(personUuid)
                        .switchIfEmpty(Mono.error(new NotFoundException("person with id " + personUuid + " not found"))),
                carRepository.findCarByUuid(carUuid)
                        .switchIfEmpty(Mono.error(new NotFoundException("car with id " + carUuid + " not found"))),
                (person, car) -> {
                    person.getCars().add(car);
                    car.setOwner(person);
                    return Mono.zip(
                            personRepository.save(person),
                            carRepository.save(car),
                            (savedPerson, savedCar) -> savedPerson
                    );
                }
        ).flatMap(r -> r)
        .onErrorResume(Exception.class, Mono::error);
    }

    @QueryMapping
    public Flux<Person> getAllPeople() {
        return personRepository.findAll();
    }

}
