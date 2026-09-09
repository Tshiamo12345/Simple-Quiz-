package com.example.simplequiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String questionId;

    @NotBlank(message = "Question Text Cannot be empty")
    @Column(nullable = false)
    @NotNull(message = "Question task can not be empty ")
    private String questionText;

    @NotBlank(message = "Question ")
    @Column(nullable = false)
    private String optionA;


    @NotBlank(message = "option Question can not be empty")
    @Column(nullable = false)
    private String optionB;

    @NotBlank(message = "option question can not be empty")
    @Column(nullable = false)
    private String optionC;

    @NotBlank(message = "correct answer can not be null")
    @Column(nullable = false)
    private String correctAnswer;

    public Question() {
    }


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
