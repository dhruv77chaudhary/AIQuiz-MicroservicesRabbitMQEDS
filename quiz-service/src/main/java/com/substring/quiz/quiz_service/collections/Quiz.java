package com.substring.quiz.quiz_service.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quizes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
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


}