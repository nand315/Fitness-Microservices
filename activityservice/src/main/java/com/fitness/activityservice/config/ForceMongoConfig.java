package com.fitness.activityservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.fitness.activityservice")
public class ForceMongoConfig {

    @Bean
    @Primary
    public MongoClient mongoClient() {
        System.out.println("🔥 FORCING MongoDB connection to: fitnessactivity");
        return MongoClients.create("mongodb://localhost:27017/fitnessactivity");
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        System.out.println("🔥 FORCING MongoTemplate to use: fitnessactivity");
        return new MongoTemplate(mongoClient(), "fitnessactivity");
    }
}