package com.example.graphtutorial.configuration;

import com.example.graphtutorial.model.BaseDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static java.util.Objects.isNull;

@Configuration
public class MongoConfiguration {

    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return () -> Mono.just("auditor");
    }

    @Bean
    public AbstractMongoEventListener<BaseDocument> personAbstractMongoEventListener() {
        return new AbstractMongoEventListener<>() {
            @Override
            public void onBeforeConvert(BeforeConvertEvent<BaseDocument> event) {
                super.onBeforeConvert(event);
                final BaseDocument baseDocument = event.getSource();
                if (isNull(baseDocument.getUuid()))
                    baseDocument.setUuid(UUID.randomUUID().toString());
            }

            @Override
            public void onAfterSave(AfterSaveEvent<BaseDocument> event) {
                super.onAfterSave(event);
            }
        };
    }

    @Bean
    public ReactiveMongoTransactionManager reactiveMongoTransactionManager(ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory) {
        return new ReactiveMongoTransactionManager(reactiveMongoDatabaseFactory);
    }

}
