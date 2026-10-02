package com.substring.quiz.quiz_service.repository;

import com.substring.quiz.quiz_service.collections.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepo extends MongoRepository<Quiz,String> {

    List<Quiz> findByTitle();
List<Quiz> findByCategoryId(String categoryId);

}
