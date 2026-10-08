package com.example.simplequiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateQuestionRequestDTO(

        @NotBlank(message = "Question text is required")
        @Size(max = 500)
        String questionText,

        @NotBlank(message = "Option A is required")
        String optionA,

        @NotBlank(message = "Option B is required")
        String optionB,

        @NotBlank(message = "Option C is required")
        String optionC,

        @NotBlank(message = "Correct answer is required")
        String correctAnswer
) {}