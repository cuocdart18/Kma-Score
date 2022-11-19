package com.example.kmascore.models;

public class Score {
    private Subject subject;
    private String firstComponentScore;
    private String secondComponentScore;
    private String examScore;
    private String avgScore;
    private String alphabetScore;
    private int index;

    public Score(Subject subject, String firstComponentScore, String secondComponentScore, String examScore, String avgScore, String alphabetScore) {
        this.subject = subject;
        this.firstComponentScore = firstComponentScore;
        this.secondComponentScore = secondComponentScore;
        this.examScore = examScore;
        this.avgScore = avgScore;
        this.alphabetScore = alphabetScore;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getFirstComponentScore() {
        return firstComponentScore;
    }

    public void setFirstComponentScore(String firstComponentScore) {
        this.firstComponentScore = firstComponentScore;
    }

    public String getSecondComponentScore() {
        return secondComponentScore;
    }

    public void setSecondComponentScore(String secondComponentScore) {
        this.secondComponentScore = secondComponentScore;
    }

    public String getExamScore() {
        return examScore;
    }

    public void setExamScore(String examScore) {
        this.examScore = examScore;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    public String getAlphabetScore() {
        return alphabetScore;
    }

    public void setAlphabetScore(String alphabetScore) {
        this.alphabetScore = alphabetScore;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
