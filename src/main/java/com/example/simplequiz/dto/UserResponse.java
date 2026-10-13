package com.example.simplequiz.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserResponse(


        String id,
        String name,
        String username,
        String email,
        LocalDate joinedDate

) {}
