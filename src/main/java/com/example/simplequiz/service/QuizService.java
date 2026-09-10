package com.example.simplequiz.service;

import com.example.simplequiz.dto.QuizRequest;
import com.example.simplequiz.exception.NotFoundException;
import com.example.simplequiz.exception.ServerException;
import com.example.simplequiz.model.Quiz;
import com.example.simplequiz.model.User;
import com.example.simplequiz.repository.QuestionRepo;
import com.example.simplequiz.repository.QuizAttemptRepo;
import com.example.simplequiz.repository.QuizRepo;
import com.example.simplequiz.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    private final Logger logger = LoggerFactory.getLogger(QuizService.class);
    private final QuizRepo quizRepo;
    private final UserRepo userRepo;
    private final QuizAttemptRepo quizAttemptRepo;
    private final QuestionRepo questionRepo;

    public QuizService(QuizRepo quizRepo,UserRepo userRepo, QuizAttemptRepo quizAttemptRepo,QuestionRepo questionRepo){
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
        this.quizAttemptRepo = quizAttemptRepo;
        this.questionRepo = questionRepo;
    }

    @Transactional(readOnly = true)
    public List<QuizRequest> getAllQuizzies(UserDetails userDetails){

        try{
            //declaration
            List<Quiz> quizzes  = quizRepo.findAll();
            List<QuizRequest> quizRequestList = new ArrayList<>();
            QuizRequest quizRequest;

            //helper method to find user
            User user = findUserByUserDetails(userDetails);

            //iteration
            for(Quiz quiz : quizzes){
                int numberOfQuestions = (int)questionRepo.countByQuizId(quiz.getId());
                boolean isTaken = quizAttemptRepo.existsByUserUsernameAndQuizId(user.getUsername(),quiz.getId());
                quizRequest = new QuizRequest();
                quizRequest.setTitle(quiz.getTitle());
                quizRequest.setTaken(isTaken);
                quizRequest.setNumberOfQuestions(numberOfQuestions);
                quizRequest.setId(quiz.getId());
                quizRequestList.add(quizRequest);

            }

            return quizRequestList;

        }catch(Exception e){
            logger.error("Something went with the server ");
            throw new ServerException("Something went wrong with the server ",e);

        }
    }

    //helper method to find user
    private User findUserByUserDetails(UserDetails userDetails)throws NotFoundException {

        Optional<User> userOptional = userRepo.findByUsername(userDetails.getUsername());

        if(userOptional.isEmpty()) throw new NotFoundException("User not found ");
        return userOptional.get();
    }

}
