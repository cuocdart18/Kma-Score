package com.example.kmascore.viewmodels;

import android.text.Editable;
import android.util.Log;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.models.SearchResult;
import com.example.kmascore.presenters.ScorePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ScoreViewModel {
    private static final String TAG = ScoreViewModel.class.getSimpleName();
    private ScorePresenter scorePresenter;

    public ScoreViewModel(ScorePresenter scorePresenter) {
        this.scorePresenter = scorePresenter;
    }

    public void onClickBtnGithub() {
        scorePresenter.onNavigateUrl();
    }

    public void onClickBtnSearch() {
        scorePresenter.onSearchData();
    }
}
