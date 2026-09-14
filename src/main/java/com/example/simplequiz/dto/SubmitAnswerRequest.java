package com.example.simplequiz.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class SubmitAnswerRequest {

    @NotEmpty(message = "answers must not be empty")
    @Valid                                  // ← cascades validation into the list
    private List<AnswerRequestDTO> answers;

    public SubmitAnswerRequest() {
    }

    public SubmitAnswerRequest(List<AnswerRequestDTO> answers) {
        this.answers = answers;
    }

    public List<AnswerRequestDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequestDTO> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "SubmitAnswerRequest{" +
                "answers=" + answers +
                '}';
    }
}

