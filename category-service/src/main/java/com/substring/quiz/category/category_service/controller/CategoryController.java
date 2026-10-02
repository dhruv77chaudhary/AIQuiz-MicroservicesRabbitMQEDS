package com.substring.quiz.category.category_service.controller;


import com.substring.quiz.category.category_service.Dto.CategoryDto;
import com.substring.quiz.category.category_service.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
@PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
       CategoryDto categoryDtos=  this.categoryService.createCategory(categoryDto);
       return new ResponseEntity<>(categoryDtos, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryDto(@PathVariable("id") String id,@RequestBody CategoryDto categoryDto){
            CategoryDto dto= this.categoryService.updateCategory(id, categoryDto);
            return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletCategory(@PathVariable("id")String id){
         this.categoryService.deleteCatgeory(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") String id){
        CategoryDto dto = this.categoryService.getCategory(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> dto = this.categoryService.getAllCategories();
return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
