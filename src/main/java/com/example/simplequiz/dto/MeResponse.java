// com/example/simplequiz/dto/MeResponse.java
package com.example.simplequiz.dto;

public record MeResponse(
        String userId,
        String username,
        String email,
        String role          // "USER", "ADMIN", etc. — no ROLE_ prefix
) {}