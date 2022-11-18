package com.example.kmascore.models;

public class StatisticSubject {
    private int failedStudents;
    private int passedStudents;

    public StatisticSubject(int failedStudents, int passedStudents) {
        this.failedStudents = failedStudents;
        this.passedStudents = passedStudents;
    }

    public int getFailedStudents() {
        return failedStudents;
    }

    public void setFailedStudents(int failedStudents) {
        this.failedStudents = failedStudents;
    }

    public int getPassedStudents() {
        return passedStudents;
    }

    public void setPassedStudents(int passedStudents) {
        this.passedStudents = passedStudents;
    }
}
