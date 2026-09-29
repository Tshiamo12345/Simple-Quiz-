package com.example.simplequiz.dto;

public class CreateQuizRequest {

    private String title;

    private String description;

    private String language;


    public CreateQuizRequest() {
    }

    public CreateQuizRequest(String title, String description, String language) {
        this.title = title;
        this.description = description;
        this.language = language;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateQuizRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
