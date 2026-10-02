package com.substring.quiz.quiz_service.projectConfig;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
@EnableFeignClients
@Configuration
public class ProjectConfig {
    @Bean

    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
@Bean
@LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
}
@LoadBalanced
@Bean
    public WebClient.Builder webClient(){
        return WebClient.builder();
}
}
