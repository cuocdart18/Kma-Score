package com.example.kmascore.viewmodels;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.models.Student;
import com.example.kmascore.models.StudentResult;
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
                        Log.e(TAG, "on subscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull StudentResult studentResult) {
                        if (studentResult != null && studentResult.getStatusCode() == 200) {
                            studentObservable.set(studentResult.getData());
                            // update data to UI
                            studentScoresPresenter.bindingStudentData(studentObservable.get());
                        } else {
                            Log.e(TAG, "error observer");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "error observer");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "completed observer");
                    }
                });
    }

    public void onClickTvKitFooter() {
        studentScoresPresenter.openUrlFromTvKitFooter();
    }
}
