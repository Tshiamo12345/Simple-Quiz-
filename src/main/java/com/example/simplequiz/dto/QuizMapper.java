package com.example.simplequiz.dto;

import com.example.simplequiz.model.Question;
import com.example.simplequiz.model.Quiz;
import com.example.simplequiz.model.User;

import java.util.List;

public final class QuizMapper {

    private QuizMapper() {}

    public static Quiz toEntity(CreateQuizRequestDTO req, User author) {
        Quiz quiz = new Quiz();
        quiz.setTitle(req.title());
        quiz.setLanguage(req.language());
        quiz.setDescription(req.description());
        quiz.setAuthor(author);

        List<Question> questions = req.questions().stream()
                .map(q -> toQuestionEntity(q, quiz))
                .toList();

        // Since Quiz.questions has cascade = ALL + orphanRemoval,
        // setting the list here persists the questions with the quiz.
        quiz.getQuestions().addAll(questions);
        return quiz;
    }

    private static Question toQuestionEntity(CreateQuestionRequestDTO req, Quiz quiz) {
        Question q = new Question();
        q.setQuestionText(req.questionText());
        q.setOptionA(req.optionA());
        q.setOptionB(req.optionB());
        q.setOptionC(req.optionC());
        q.setCorrectAnswer(req.correctAnswer());
        q.setQuiz(quiz);          // <-- set the owning side
        return q;
    }

    public static QuizResponse toResponse(Quiz quiz) {
        List<QuestionResponse> questions = quiz.getQuestions().stream()
                .map(QuizMapper::toQuestionResponse)
                .toList();

        return new QuizResponse(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getLanguage(),
                quiz.getDescription(),
                quiz.getAuthor() != null ? quiz.getAuthor().getUsername() : null,
                questions.size(),
                questions
        );
    }

    private static QuestionResponse toQuestionResponse(Question q) {
        return new QuestionResponse(
                q.getQuestionId(),
                q.getQuestionText(),
                q.getOptionA(),
                q.getOptionB(),
                q.getOptionC()
        );
    }
}