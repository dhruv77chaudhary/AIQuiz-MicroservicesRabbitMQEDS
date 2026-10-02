package com.substring.quiz.quiz_service.service;

import com.substring.quiz.quiz_service.dto.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
public class CategoryServiceImpl implements  CategoryService{

    private RestTemplate restTemplate;
    private ModelMapper modelMapper;
    private WebClient webClient;
private WebClient.Builder webClientbuilder;
    public CategoryServiceImpl(RestTemplate restTemplate, ModelMapper modelMapper, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.webClientbuilder = webClientBuilder;
       this.webClient= webClientbuilder.baseUrl("https://Category-Service:9091").build();
    }

    @Override
    public CategoryDto findById(String categoryId) {
        CategoryDto categoryDto = this.webClient.get().uri("/api/v1/categories/{categoryId}",categoryId).retrieve().
                bodyToMono(CategoryDto.class).block();
        return categoryDto;
    }

    @Override
    public List<CategoryDto> findAll() {
        return List.of();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
         return this.webClient.post().uri("/api/v1/categories").bodyValue(categoryDto).retrieve().bodyToMono(CategoryDto.class).block();

    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void delete(String categoryId) {

    }
}
