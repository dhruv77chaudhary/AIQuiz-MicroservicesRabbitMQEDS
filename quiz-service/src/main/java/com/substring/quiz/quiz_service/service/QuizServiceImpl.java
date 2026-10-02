package com.substring.quiz.quiz_service.service;

import com.substring.quiz.quiz_service.collections.Quiz;
import com.substring.quiz.quiz_service.dto.CategoryDto;
import com.substring.quiz.quiz_service.dto.QuizDto;
import com.substring.quiz.quiz_service.repository.QuizRepo;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@Service
public class QuizServiceImpl implements QuizService{
    private QuizRepo quizRepo;
    private ModelMapper modelMapper;
    private Logger loger= org.slf4j.LoggerFactory.getLogger(QuizServiceImpl.class);
    private CategoryService categoryService;
private WebClient webClient;
    private RestTemplate restTemplate;
private StreamBridge streamBridge;
    private CategoryServiceFeign categoryServiceFeign;
    public QuizServiceImpl(QuizRepo quizRepo, ModelMapper modelMapper,RestTemplate restTemplate,CategoryService categoryService,CategoryServiceFeign categoryServiceFeign,StreamBridge streamBridge ) {
        this.quizRepo = quizRepo;
        this.modelMapper = modelMapper;
        this.restTemplate=restTemplate;
        this.categoryService=categoryService;
        this.categoryServiceFeign=categoryServiceFeign;
this.streamBridge=streamBridge;
    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz= modelMapper.map(quizDto,Quiz.class);
        quiz.setId(UUID.randomUUID().toString());
        CategoryDto categoryDto = restTemplate.getForObject("http://CATEGORY-SERVICE/api/v1/categories/"+quizDto.getCategoryId(), CategoryDto.class);
        loger.info("categpry exists "+categoryDto.getTitle());

        Quiz quiz1= quizRepo.save(quiz);
        QuizDto quizDto1 = modelMapper.map(quiz1,QuizDto.class);
        quizDto1.setCategoryDto(categoryDto);

        pulicQuizCreatedEvent(quizDto);

        return quizDto1;


    }
//event publish
    private void pulicQuizCreatedEvent(QuizDto quizDto) {

loger.info("quiz created going to publish quiz created event ");
     var success = this.streamBridge.send("quizCreatedBinding-out-0",quizDto);
        if(success)
            loger.info("event is send to broker ");
        else
            loger.info("event is not send to broker ");
    }

    @Override
    public QuizDto updateQuiz(String quizId, QuizDto quizDto) {
        Quiz quiz= quizRepo.findById(quizId).orElseThrow(()-> new NoSuchElementException("No quiz"));
        quiz.setCategoryId(quizDto.getCategoryId());
        quiz.setDescription(quizDto.getDescription());
        quiz.setTitle(quizDto.getTitle());
        quiz.setImageUrl(quizDto.getImageUrl());
        quiz.setNoOfQuestion(quizDto.getNoOfQuestion());
        quiz.setMaxMarks(quizDto.getMaxMarks());
        quiz.setLive(quizDto.getLive());
        quiz.setCreatedBy(quizDto.getCreatedBy());
        quiz.setTimeLimit(quizDto.getTimeLimit());

        return modelMapper.map(quiz,QuizDto.class);
    }

    @Override
    public void delete(String quizId) {
        Quiz quiz= quizRepo.findById(quizId).orElseThrow(()-> new NoSuchElementException("No quiz"));
        quizRepo.delete(quiz);
    }

    @Override
    public List<QuizDto> findAll() {
        List<Quiz>  all = quizRepo.findAll();
       return all.stream().map(quiz -> {
            String categoryId= quiz.getCategoryId();

        QuizDto quizDto =modelMapper.map(quiz,QuizDto.class);
  CategoryDto categoryDto =categoryService.findById(categoryId);
  quizDto.setCategoryDto(categoryDto);
  return quizDto;
        }).toList();

    }

    @Override
    public List<QuizDto> findByCategory(String categoryId) {
        List<Quiz> quizes = quizRepo.findByCategoryId(categoryId);

        return quizes.stream().map(quiz -> {
            QuizDto quizDto= modelMapper.map(quiz,QuizDto.class);
CategoryDto categoryDto=categoryServiceFeign.findById(categoryId);
quizDto.setCategoryDto(categoryDto);
      return quizDto;
        }).toList();
    }

    @Override
    public QuizDto findById(String quizId) {

        Quiz quiz= quizRepo.findById(quizId).orElseThrow(()-> new NoSuchElementException("No quiz"));
        QuizDto quizDto=  modelMapper.map(quiz,QuizDto.class);
        String categoryId= quiz.getCategoryId();
        String url="http://CATEGORY-SERVICE/api/v1/categories/"+categoryId;
        loger.info(url);
        CategoryDto categoryDto=restTemplate.getForObject(url, CategoryDto.class);
        quizDto.setCategoryDto(categoryDto);
        return quizDto;

    }
}
