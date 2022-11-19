package com.example.kmascore.models;

public class StatisticsResult extends Result {
    private Statistic data;

    public StatisticsResult(Statistic data, String message, int statusCode) {
        super(message, statusCode);
        this.data = data;
    }

    public Statistic getData() {
        return data;
    }

    public void setData(Statistic statistic) {
        this.data = statistic;
    }
}
