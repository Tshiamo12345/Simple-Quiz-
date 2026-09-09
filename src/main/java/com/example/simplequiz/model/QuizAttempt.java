package com.example.simplequiz.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_Id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id",nullable = false)
    private Quiz quiz;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double score;


    public QuizAttempt() {
    }

    public QuizAttempt(String id, User user, Quiz quiz, LocalDateTime startTime, LocalDateTime endTime, Double score) {
        this.id = id;
        this.user = user;
        this.quiz = quiz;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuizAttempt{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", quiz=" + quiz +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", score=" + score +
                '}';
    }

}
