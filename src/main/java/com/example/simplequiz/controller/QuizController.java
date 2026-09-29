package com.example.simplequiz.controller;


import com.example.simplequiz.dto.QuizQuestionsRequest;
import com.example.simplequiz.dto.QuizRequest;
import com.example.simplequiz.dto.QuizResultResponse;
import com.example.simplequiz.dto.SubmitAnswerRequest;
import com.example.simplequiz.model.Question;
import com.example.simplequiz.model.User;
import com.example.simplequiz.service.QuizService;
import com.example.simplequiz.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/quiz")
@RestController
public class QuizController {

    private final QuizService quizService;
    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    public QuizController(QuizService quizService,UserService userService) {
        this.quizService = quizService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<QuizRequest>> getQuizzes(@AuthenticationPrincipal UserDetails userDetails) {
        List<QuizRequest> quizRequestList = quizService.getAllQuizzes(userDetails);
        return ResponseEntity.ok(quizRequestList);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/start/{quizId}")
    public ResponseEntity<List<QuizQuestionsRequest>> getAllQuizQuestions(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String quizId) {
        List<QuizQuestionsRequest> questionList = quizService.getAllQuizQuestions(userDetails, quizId);
        return ResponseEntity.ok(questionList);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizResultResponse> submitAnswers(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String quizId,
            @Valid @RequestBody SubmitAnswerRequest request) {
        QuizResultResponse quizResultResponse = quizService.gradeQuiz(userDetails,quizId,request);
    return ResponseEntity.ok(quizResultResponse);

    }
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@AuthenticationPrincipal UserDetails userDetails,@PathVariable String quizId){

        quizService.delete(quizId,userDetails);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }


}
