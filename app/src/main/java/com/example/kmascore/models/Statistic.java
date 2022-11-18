package com.example.kmascore.models;

public class Statistic {
    private int numberOfDebtors;
    private int numberOfStudents;
    private int numberOfSubjects;

    public Statistic(int numberOfDebtors, int numberOfStudents, int numberOfSubjects) {
        this.numberOfDebtors = numberOfDebtors;
        this.numberOfStudents = numberOfStudents;
        this.numberOfSubjects = numberOfSubjects;
    }

    public int getNumberOfDebtors() {
        return numberOfDebtors;
    }

    public void setNumberOfDebtors(int numberOfDebtors) {
        this.numberOfDebtors = numberOfDebtors;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public int getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }
}
