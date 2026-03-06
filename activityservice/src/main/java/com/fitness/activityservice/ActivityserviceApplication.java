package com.fitness.activityservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ActivityserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityserviceApplication.class, args);
    }

    @Bean
    public CommandLineRunner checkDatabase(MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("==========================================");
            System.out.println("🔍 VERIFYING DATABASE CONNECTION:");
            System.out.println("Connected to: '" + mongoTemplate.getDb().getName() + "'");
            System.out.println("Database object: " + mongoTemplate.getDb());
            System.out.println("==========================================");
        };
    }
}