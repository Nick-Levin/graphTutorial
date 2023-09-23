package com.example.graphtutorial.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document("cars")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@CompoundIndexes({
        @CompoundIndex(name = "manufacturer_model", def = "{'manufacturer':1,'model':1}")
})
public class Car extends BaseDocument {

    private String manufacturer;
    private String model;
    private String color;

    @Indexed
    private String serialNumber;

    @DocumentReference(lazy = true, lookup = "{'uuid': ?#{#target}}")
    private Person owner;

}
