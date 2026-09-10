package com.example.simplequiz.service;

import com.example.simplequiz.dto.QuizRequest;
import com.example.simplequiz.exception.ServerException;
import com.example.simplequiz.model.Quiz;
import com.example.simplequiz.repository.QuizRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {


    private final Logger logger = LoggerFactory.getLogger(QuizService.class);
    private final QuizRepo quizRepo;

    public QuizService(QuizRepo quizRepo){
        this.quizRepo = quizRepo;
    }

    public List<QuizRequest> getAllQuizzies(){

        try{
            List<Quiz> quizzes  = quizRepo.findAll();

            

        }catch(Exception e){
            logger.error("Something went with the server ");
            throw new ServerException("Something went wrong with the server ");

        }

    }

}
