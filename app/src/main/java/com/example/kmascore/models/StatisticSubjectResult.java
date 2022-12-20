package com.example.kmascore.models;

public class StatisticSubjectResult extends Result {
    private StatisticSubject data;

    public StatisticSubjectResult(StatisticSubject data, String message, int statusCode) {
        super(message, statusCode);
        this.data = data;
    }

    public StatisticSubject getData() {
        return data;
    }

    public void setData(StatisticSubject data) {
        this.data = data;
    }
}
