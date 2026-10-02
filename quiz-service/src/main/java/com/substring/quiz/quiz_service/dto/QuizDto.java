package com.substring.quiz.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private String id;
    private String title;
    private String description;
    private String categoryId;
    private Integer maxMarks;
    private String createdBy;
    private Integer timeLimit;
    private Integer noOfQuestion;
    private Integer passPercentage;
    private Boolean live;
    private String imageUrl;
    private CategoryDto categoryDto;
}
