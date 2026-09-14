package com.example.simplequiz.controller;


import com.example.simplequiz.dto.QuizQuestionsRequest;
import com.example.simplequiz.dto.QuizRequest;
import com.example.simplequiz.model.Question;
import com.example.simplequiz.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/quiz")
@RestController
public class QuizController {

    private final QuizService quizService;

    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<QuizRequest>> getQuizzes(@AuthenticationPrincipal UserDetails userDetails) {
        List<QuizRequest> quizRequestList = quizService.getAllQuizzes(userDetails);
        return ResponseEntity.ok(quizRequestList);
    }

    @GetMapping("/start/{quizId}")
    public ResponseEntity<List<QuizQuestionsRequest>> getAllQuizQuestions(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String quizId) {
        List<QuizQuestionsRequest> questionList = quizService.getAllQuizQuestions(userDetails, quizId);
        return ResponseEntity.ok(questionList);
    }


}
