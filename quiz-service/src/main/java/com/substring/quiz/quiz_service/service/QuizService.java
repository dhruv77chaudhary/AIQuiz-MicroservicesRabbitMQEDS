package com.substring.quiz.quiz_service.service;

import com.substring.quiz.quiz_service.dto.QuizDto;
import java.util.*;
public interface QuizService {
    QuizDto createQuiz(QuizDto quizDto);
    QuizDto updateQuiz(String quizId,QuizDto quizDto);
    void delete(String quizId);
    List<QuizDto> findAll();
List<QuizDto> findByCategory(String categoryId);
QuizDto findById(String quizId);
}
