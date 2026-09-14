package com.example.simplequiz.dto;

public class QuizQuestionsRequest {

    private String questionId;

    private String optionA;

    private String optionB;

    private String optionC;

    private String questionText;

    public QuizQuestionsRequest(String questionId, String optionA, String optionB, String optionC, String questionText) {
        this.questionId = questionId;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.questionText = questionText;
    }

    public QuizQuestionsRequest() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public String toString() {
        return "QuizQuestionsRequest{" +
                "questionId='" + questionId + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}
