package com.example.kmascore.presenters;

import android.content.Context;

import com.example.kmascore.models.MiniStudent;

import java.util.List;

public interface SearchDataPresenter {

    void showMiniStudentToUI(List<MiniStudent> miniStudents);
}
