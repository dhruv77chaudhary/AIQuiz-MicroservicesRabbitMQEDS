package com.substring.quiz.quiz_service.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String quizId;
    private String title;
    private String description;
    private boolean active;
}
