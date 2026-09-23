package com.example.simplequiz.service;

import com.example.simplequiz.dto.*;
import com.example.simplequiz.exception.NotFoundException;
import com.example.simplequiz.exception.ServerException;
import com.example.simplequiz.model.Question;
import com.example.simplequiz.model.Quiz;
import com.example.simplequiz.model.QuizAttempt;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<QuizRequest> getAllQuizzes(UserDetails userDetails){

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
                quizRequest.setAuthor(quiz.getAuthor().getUsername());
                quizRequest.setDescription(quiz.getDescription());
                quizRequestList.add(quizRequest);

            }

            return quizRequestList;

        }catch(Exception e){
            logger.error("Something went with the server ",e);
            throw new ServerException("Something went wrong with the server ");

        }
    }

    //helper method to find user
    private User findUserByUserDetails(UserDetails userDetails)throws NotFoundException {

        Optional<User> userOptional = userRepo.findByUsername(userDetails.getUsername());

        if(userOptional.isEmpty()) throw new NotFoundException("User not found ");
        return userOptional.get();
    }

    public List<QuizQuestionsRequest> getAllQuizQuestions(UserDetails userDetails, String quizId)throws NotFoundException {

        List<QuizQuestionsRequest> quizQuestionsRequests;
        try{
            logger.info("Preparing to get all questions {}",QuizService.class);
            //checking if user exist by token
            User user = findUserByUserDetails(userDetails);
            // returning questions by quiz
            List<Question> questions = questionRepo.findByQuizId(quizId);
            quizQuestionsRequests = new ArrayList<>();
            for(Question question: questions){

                QuizQuestionsRequest quizQuestionsRequest = new QuizQuestionsRequest();
                quizQuestionsRequest.setQuestionId(question.getQuestionId());
                quizQuestionsRequest.setQuestionText(question.getQuestionText());
                quizQuestionsRequest.setOptionA(question.getOptionA());
                quizQuestionsRequest.setOptionB(question.getOptionB());
                quizQuestionsRequest.setOptionC(question.getOptionC());
                quizQuestionsRequests.add(quizQuestionsRequest);
            }
        return quizQuestionsRequests;
        }catch(ServerException serverException){
            logger.error("Something went wrong with the server ",serverException);
            throw new ServerException("Something went wrong with the server");
        }

    }

    @Transactional
    public QuizResultResponse gradeQuiz(UserDetails userDetails, String quizId, SubmitAnswerRequest submission) {

        User user = findUserByUserDetails(userDetails);

        List<Question> questions = questionRepo.findByQuizId(quizId);
        if(questions.isEmpty()){

            throw new NotFoundException("No questions for quiz "+ quizId);
        }

        Map<String, String> answersByQuestionId = submission.getAnswers().stream()
                .filter(a -> a.getQuestionId() != null && a.getChosenAnswer() != null)
                .collect(Collectors.toMap(
                        AnswerRequestDTO::getQuestionId,
                        AnswerRequestDTO::getChosenAnswer,
                        (a, b) -> b   // last-wins on duplicate questionId
                ));
        int correct = 0;

        List<QuizResultResponse.QuestionResult> details = new ArrayList<>();

        for (Question q : questions) {
            String chosen = answersByQuestionId.get(q.getQuestionId());

            // DB stores the letter ("a"/"b"/"c"), not the text — resolve it to the option text
            String correctLetter = q.getCorrectAnswer() == null
                    ? ""
                    : q.getCorrectAnswer().trim().toLowerCase();

            String correctText = switch (correctLetter) {
                case "a" -> q.getOptionA();
                case "b" -> q.getOptionB();
                case "c" -> q.getOptionC();
                default -> null;
            };

            // Trim both sides to survive accidental whitespace
            String chosenTrimmed = chosen == null ? null : chosen.trim();
            String correctTrimmed = correctText == null ? null : correctText.trim();

            boolean isCorrect = chosenTrimmed != null
                    && correctTrimmed != null
                    && chosenTrimmed.equalsIgnoreCase(correctTrimmed);

            if (isCorrect) correct++;

            details.add(new QuizResultResponse.QuestionResult(
                    q.getQuestionId(),
                    chosen,                 // still return the text the user picked
                    correctText,            // return the TEXT — the frontend compares to option text
                    isCorrect
            ));
        }

        int total = questions.size();
        int score = (int) Math.round((correct * 100.0)/ total);

        QuizAttempt quizAttempt = new QuizAttempt();
        Optional<Quiz>  optionalQuiz  = quizRepo.findById(quizId);
        if(optionalQuiz.isEmpty()){
            throw new NotFoundException("Quiz not found with id "+ quizId);
        }
        quizAttempt.setQuiz(optionalQuiz.get());
        quizAttempt.setEndTime(LocalDateTime.now());
        quizAttempt.setScore((double) score);
        quizAttempt.setUser(user);
        quizAttemptRepo.save(quizAttempt);

        return new QuizResultResponse(total,correct,score,details);
    }
}
