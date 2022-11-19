package com.example.kmascore.models;

public class StudentResult extends Result {
    private Student data;

    public StudentResult(Student data, String message, int statusCode) {
        super(message, statusCode);
        this.data = data;
    }

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }
}
