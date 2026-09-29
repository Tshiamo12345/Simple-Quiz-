package com.example.simplequiz.dto;

public class QuizRequest {

    private String title;

    private String id;

    private int numberOfQuestions;

    private String author;

    private boolean isTaken;

    private String description;

    private String language;

    public String getAuthor() {
        return author;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public QuizRequest() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public String toString() {
        return "QuizRequest{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", numberOfQuestions=" + numberOfQuestions +
                ", author='" + author + '\'' +
                '}';
    }
}
