package com.example.simplequiz.dto;

import java.util.List;

public record QuizResponse(
        String id,
        String title,
        String language,
        String description,
        String authorUsername,
        int numberOfQuestions,
        List<QuestionResponse> questions
) {}