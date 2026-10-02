package com.substring.quiz.quiz_service.controller;

import com.substring.quiz.quiz_service.dto.QuizDto;
import com.substring.quiz.quiz_service.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("api/v1/quizzes")

public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @PostMapping

    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto){
        return ResponseEntity.ok(quizService.createQuiz(quizDto));
    }
    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable String  quizId,@RequestBody QuizDto quizDto){
        return new ResponseEntity<>(quizService.updateQuiz(quizId,quizDto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuiz(){
        return new ResponseEntity<>(quizService.findAll(),HttpStatus.OK);
    }
@DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable String quizId){
       quizService.delete(quizId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
@GetMapping("/{quizId}")
    public ResponseEntity<QuizDto> getById(@PathVariable String quizId){
        return new ResponseEntity<>(quizService.findById(quizId),HttpStatus.OK);
}
@GetMapping("/category/{categoryId}")
    public ResponseEntity <List<QuizDto>> getByCategory(@PathVariable String categoryId){
        return new ResponseEntity<>(quizService.findByCategory(categoryId),HttpStatus.OK);
}

}
