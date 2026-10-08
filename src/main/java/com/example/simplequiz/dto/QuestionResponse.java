package com.example.simplequiz.dto;

public record QuestionResponse(
        String questionId,
        String questionText,
        String optionA,
        String optionB,
        String optionC
) {}