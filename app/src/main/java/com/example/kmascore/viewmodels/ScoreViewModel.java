package com.example.kmascore.viewmodels;

import com.example.kmascore.presenters.ScorePresenter;

public class ScoreViewModel {
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
