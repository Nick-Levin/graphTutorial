package com.example.graphtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
public class GraphTutorialApplication {

    public static void main(String ...args) {
        SpringApplication.run(GraphTutorialApplication.class, args);
    }

}
