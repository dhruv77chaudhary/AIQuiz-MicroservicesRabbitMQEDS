package com.substring.quiz.quiz_service.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

@Configuration
public class QuizService {
    private Logger logger = Logger.getLogger(QuizService.class.getName());

    @Bean(name = "getQuizBinding")
    public Function<QuizDto,String> getQuizBinding() {
        return (quizDto -> {
            logger.info("message recived");
            System.out.println("Quiz created event recived");
            System.out.println(quizDto.getTitle());
            return "Quiz created successfully ";
        });

    }
}
