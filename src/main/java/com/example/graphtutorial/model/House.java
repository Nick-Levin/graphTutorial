package com.example.graphtutorial.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("houses")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class House extends BaseDocument {

    private Address address;
    private Double value;
    private String valueCurrency;

    @DocumentReference(lazy = true)
    private Person owner;

}
