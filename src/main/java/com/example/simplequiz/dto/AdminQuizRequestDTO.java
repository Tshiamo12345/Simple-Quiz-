package com.example.simplequiz.dto;

public class AdminQuizRequestDTO {

    private String id;

    private String quizName;

    private String language;

    private int numberOfQuestions;

    private int numberOfAttempts;

    private String status;

    public AdminQuizRequestDTO(String quizName, String language, int numberOfQuestions, int numberOfAttempts, String status) {
        this.quizName = quizName;
        this.language = language;
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfAttempts = numberOfAttempts;
        this.status = status;
    }

    public AdminQuizRequestDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminQuizRequestDTO{" +
                "id='" + id + '\'' +
                ", quizName='" + quizName + '\'' +
                ", language='" + language + '\'' +
                ", numberOfQuestions=" + numberOfQuestions +
                ", numberOfAttempts=" + numberOfAttempts +
                ", status='" + status + '\'' +
                '}';
    }
}
