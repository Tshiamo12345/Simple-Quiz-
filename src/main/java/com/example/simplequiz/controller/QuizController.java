package com.example.simplequiz.controller;


import com.example.simplequiz.dto.QuizRequest;
import com.example.simplequiz.exception.ServerException;
import com.example.simplequiz.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/quiz")
@RestController
public class QuizController {

    private final QuizService quizService;

    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    public QuizController(QuizService quizService){

        this.quizService = quizService;
    }


    @GetMapping
    public ResponseEntity<List<QuizRequest>> getQuizzies(@AuthenticationPrincipal UserDetails userDetails){

        try{

            List<QuizRequest> quizRequestList = quizService.getAllQuizzies(userDetails);
            return new ResponseEntity<>(quizRequestList,HttpStatus.OK);
        }catch(ServerException ex){
            logger.error("Something went wrong with the server ",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
