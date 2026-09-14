package com.example.simplequiz.dto;

import java.util.List;

public class QuizResultResponse {

    private int total;

    private int correct;

    private int score;

    private List<QuestionResult> details;

    public QuizResultResponse(int total, int correct, int score, List<QuestionResult> details) {
        this.total = total;
        this.correct = correct;
        this.score = score;
        this.details = details;
    }

    public QuizResultResponse() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<QuestionResult> getDetails() {
        return details;
    }

    public void setDetails(List<QuestionResult> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "QuizResultResponse{" +
                "total=" + total +
                ", correct=" + correct +
                ", score=" + score +
                ", details=" + details +
                '}';
    }

    public static class QuestionResult {

        private String questionId;

        private String chosenAnswer;

        private String correctAnswer;

        private boolean correct;

        public QuestionResult() {
        }

        public QuestionResult(String questionId, String chosenAnswer, String correctAnswer, boolean correct) {
            this.questionId = questionId;
            this.chosenAnswer = chosenAnswer;
            this.correctAnswer = correctAnswer;
            this.correct = correct;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getChosenAnswer() {
            return chosenAnswer;
        }

        public void setChosenAnswer(String chosenAnswer) {
            this.chosenAnswer = chosenAnswer;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }

        @Override
        public String toString() {
            return "QuestionResult{" +
                    "questionId='" + questionId + '\'' +
                    ", chosenAnswer='" + chosenAnswer + '\'' +
                    ", correctAnswer='" + correctAnswer + '\'' +
                    ", correct=" + correct +
                    '}';
        }
    }

}
