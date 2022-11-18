package com.example.kmascore.models;

import java.util.List;

public class Student {
    private double avgScore;
    private String classInSchool;
    private int failedSubjects;
    private String id;
    private String name;
    private int passedSubjects;
    private List<Score> scores;

    public Student(double avgScore, String classInSchool, int failedSubjects, String id, String name, int passedSubjects, List<Score> scores) {
        this.avgScore = avgScore;
        this.classInSchool = classInSchool;
        this.failedSubjects = failedSubjects;
        this.id = id;
        this.name = name;
        this.passedSubjects = passedSubjects;
        this.scores = scores;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getClassInSchool() {
        return classInSchool;
    }

    public void setClassInSchool(String classInSchool) {
        this.classInSchool = classInSchool;
    }

    public int getFailedSubjects() {
        return failedSubjects;
    }

    public void setFailedSubjects(int failedSubjects) {
        this.failedSubjects = failedSubjects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassedSubjects() {
        return passedSubjects;
    }

    public void setPassedSubjects(int passedSubjects) {
        this.passedSubjects = passedSubjects;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
