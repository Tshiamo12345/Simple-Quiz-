package com.example.simplequiz.dto;

import jakarta.validation.constraints.NotBlank;

public class AnswerRequestDTO {


    @NotBlank(message = "chosenAnswer is required (A, B, or C)")
    private String chosenAnswer;

    @NotBlank(message = "questionId is required")
    private String questionId;


    public AnswerRequestDTO(String chosenAnswer, String questionId) {
        this.chosenAnswer = chosenAnswer;
        this.questionId = questionId;
    }

    public AnswerRequestDTO() {
    }

    public String getChosenAnswer() {
        return chosenAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
