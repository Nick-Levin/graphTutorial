package com.example.graphtutorial.repository;

import com.example.graphtutorial.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    Mono<Person> findPersonByUuid(String uuid);

}
