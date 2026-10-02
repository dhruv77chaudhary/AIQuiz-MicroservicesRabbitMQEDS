package com.substring.quiz.quiz_service.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class InformationService {
@Bean
    public Consumer<String> acknowledgeInformation(){
    return information->{
         System.out.println("Acknowledgment recived");
        System.out.println(information);
    };
}
}
