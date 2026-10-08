package com.example.simplequiz.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateQuizRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(max = 120, message = "Title must be at most 120 characters")
        String title,

        @NotBlank(message = "Language is required")
        @Size(max = 60)
        String language,

        @NotBlank(message = "Description is required")
        @Size(max = 500)
        String description,

        @NotEmpty(message = "A quiz must have at least one question")
        @Valid
        List<CreateQuestionRequestDTO> questions
) {}