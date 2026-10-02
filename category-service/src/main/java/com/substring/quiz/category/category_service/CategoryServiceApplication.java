package com.substring.quiz.category.category_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.TimeZone;
@EnableDiscoveryClient
@SpringBootApplication
public class CategoryServiceApplication {

    public static void main(String[] args) {
        // Force the JVM to use UTC before Spring even starts
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

}
