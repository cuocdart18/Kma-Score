package com.example.kmascore.presenters;

import com.example.kmascore.models.StatisticSubject;
import com.example.kmascore.models.Student;

public interface StudentScoresPresenter {
    void bindingStudentData(Student student);

    void openUrlFromTvKitFooter();

    void openStatisticSubject(StatisticSubject statisticSubject);
}
