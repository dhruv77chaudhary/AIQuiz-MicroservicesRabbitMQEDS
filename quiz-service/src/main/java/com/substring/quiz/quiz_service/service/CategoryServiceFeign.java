package com.substring.quiz.quiz_service.service;

import com.substring.quiz.quiz_service.dto.CategoryDto;
import lombok.Getter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@FeignClient(name="Category-Service")
public interface CategoryServiceFeign {
    @GetMapping("/api/v1/categories")
List<CategoryDto> findAll();

    @GetMapping("/api/v1/categories/{categoryId}")
    CategoryDto findById(@PathVariable String categoryId);
}
