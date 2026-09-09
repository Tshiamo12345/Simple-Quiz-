package com.example.simplequiz.exception;

public class AlreadyFoundException extends RuntimeException {
    public AlreadyFoundException(String message) {
        super(message);
    }
}
