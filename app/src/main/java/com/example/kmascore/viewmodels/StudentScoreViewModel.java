package com.example.kmascore.viewmodels;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.models.StatisticSubject;
import com.example.kmascore.models.StatisticSubjectResult;
import com.example.kmascore.models.Student;
import com.example.kmascore.models.StudentResult;
import com.example.kmascore.models.Subject;
import com.example.kmascore.presenters.StudentScoresPresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentScoreViewModel {
    private static final String TAG = StudentScoreViewModel.class.getSimpleName();
    public ObservableField<Student> studentObservable = new ObservableField<>();

    private Disposable disposable;
    private final StudentScoresPresenter studentScoresPresenter;

    public StudentScoreViewModel(Disposable disposable, StudentScoresPresenter studentScoresPresenter) {
        this.disposable = disposable;
        this.studentScoresPresenter = studentScoresPresenter;
    }

    public void initStudentData(String studentId) {
        IKmaScoreApi.getInstance.getStudentStatistics(studentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StudentResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "on subscribe getStudentStatistics");
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull StudentResult studentResult) {
                        if (studentResult != null && studentResult.getStatusCode() == 200) {
                            studentObservable.set(studentResult.getData());
                            // update data to UI
                            studentScoresPresenter.bindingStudentData(studentObservable.get());
                        } else {
                            Log.d(TAG, "error observer getStudentStatistics");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "error observer getStudentStatistics");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "completed observer getStudentStatistics");
                    }
                });
    }

    public void openStatisticSubject(Subject subject) {
        IKmaScoreApi.getInstance.getSubjectStatistics(subject.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StatisticSubjectResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "on subscribe getSubjectStatistics");
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull StatisticSubjectResult statisticSubjectResult) {
                        if (statisticSubjectResult != null && statisticSubjectResult.getStatusCode() == 200) {
                            StatisticSubject statisticSubject = statisticSubjectResult.getData();
                            // update data to UI
                            studentScoresPresenter.openStatisticSubject(statisticSubject);
                        } else {
                            Log.d(TAG, "error observer getSubjectStatistics");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "error observer getSubjectStatistics");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "completed observer getSubjectStatistics");
                    }
                });
    }

    public void onClickTvKitFooter() {
        studentScoresPresenter.openUrlFromTvKitFooter();
    }
}
