package com.example.kmascore.models;

import java.util.List;

public class SearchResult extends Result {
    private List<MiniStudent> data;

    public SearchResult(List<MiniStudent> data, String message, int statusCode) {
        super(message, statusCode);
        this.data = data;
    }

    public List<MiniStudent> getData() {
        return data;
    }

    public void setData(List<MiniStudent> data) {
        this.data = data;
    }
}
