package com.substring.quiz.category.category_service.service;

import com.substring.quiz.category.category_service.Dto.CategoryDto;
import com.substring.quiz.category.category_service.entities.Category;
import com.substring.quiz.category.category_service.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
public class CategoryServiceImpl implements CategoryService{
   private ModelMapper modelMapper;
   private CategoryRepo categoryRepo;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepo categoryRepo) {
        this.modelMapper = modelMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        category.setQuizId(UUID.randomUUID().toString());
        Category categorys= categoryRepo.save(category);
        return modelMapper.map(categorys,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
Category category= categoryRepo.findById(categoryId).orElseThrow(()-> new NoSuchElementException("No id exost"));
    category.setActive(categoryDto.isActive());
    category.setTitle(categoryDto.getTitle());
    category.setDescription(categoryDto.getDescription());
    Category category1 =categoryRepo.save(category);
    return modelMapper.map(category1,CategoryDto.class);
    }


    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NoSuchElementException("No category"));

        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCatgeory(String catgoryId) {
        Category category = categoryRepo.findById(catgoryId).orElseThrow(()-> new NoSuchElementException("No category"));
categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories= categoryRepo.findAll();
        return categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).toList();
    }
}
