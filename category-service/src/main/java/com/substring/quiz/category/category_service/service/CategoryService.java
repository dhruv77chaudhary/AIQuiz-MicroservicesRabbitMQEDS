package com.substring.quiz.category.category_service.service;

import com.substring.quiz.category.category_service.Dto.CategoryDto;
import java.util.*;
public interface CategoryService {
CategoryDto createCategory(CategoryDto categoryDto);
CategoryDto updateCategory(String categoryId,CategoryDto categoryDto);
CategoryDto getCategory(String categoryId);
void deleteCatgeory(String catgoryId);
List<CategoryDto> getAllCategories();
}
