package com.fitness.aiservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.fitness.aiservice")
public class ForceMongoConfig {

    @Bean
    @Primary
    public MongoClient mongoClient() {
        System.out.println("🔥 FORCING AI Service MongoDB to: fitnessrecommendation");
        return MongoClients.create("mongodb://localhost:27017/fitnessrecommendation");
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        System.out.println("🔥 FORCING AI Service MongoTemplate to use: recommendations");
        return new MongoTemplate(mongoClient(), "recommendations");
    }
}