package com.example.graphtutorial.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Document("people")
@CompoundIndexes({
        @CompoundIndex(name = "fullname", def = "{'firstname':1,'lastname':1}")
})
public class Person extends BaseDocument {

    private String firstname;
    private String lastname;
    private String gender;

    @ToString.Exclude
    @DocumentReference(lookup = "{'uuid': ?#{#target}}")
    private List<House> houses = new ArrayList<>();

    @ToString.Exclude
    @DocumentReference(lookup = "{'uuid': ?#{#target}}")
    private List<Car> cars = new ArrayList<>();

    public List<House> getHouses() {
        return Optional.ofNullable(houses).orElseGet(() -> {
            houses = new ArrayList<>();
            return this.houses;
        });
    }

    public List<Car> getCars() {
        return Optional.ofNullable(cars).orElseGet(() -> {
            cars = new ArrayList<>();
            return this.cars;
        });
    }
}
