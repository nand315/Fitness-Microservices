package com.fitness.aiservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMongoRepositories(basePackages = "com.fitness.aiservice.repository")
@Slf4j
public class ForceMongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    @Primary
    public MongoClient mongoClient() {
        log.info("🔥 Creating MongoClient with URI: {}", mongoUri);
        return MongoClients.create(mongoUri);
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        // Extract database name from URI (fitnessrecommendation)
        String databaseName = extractDatabaseNameFromUri(mongoUri);
        log.info("🔥 FORCING AI Service MongoDB to use database: {}", databaseName);

        SimpleMongoClientDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient(), databaseName);
        return new MongoTemplate(factory);
    }

    private String extractDatabaseNameFromUri(String uri) {
        // Extract database name from mongodb://localhost:27017/fitnessrecommendation
        int lastSlash = uri.lastIndexOf('/');
        if (lastSlash > 0 && lastSlash < uri.length() - 1) {
            String database = uri.substring(lastSlash + 1).split("\\?")[0];
            log.info("🔥 Extracted database name: {}", database);
            return database;
        }
        log.warn("🔥 Could not extract database name from URI, using default: fitnessrecommendation");
        return "fitnessrecommendation"; // Default fallback
    }
}