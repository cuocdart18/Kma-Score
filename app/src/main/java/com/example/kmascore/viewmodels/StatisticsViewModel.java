package com.example.kmascore.viewmodels;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.models.Statistic;
import com.example.kmascore.models.StatisticsResult;
import com.example.kmascore.presenters.StatisticsPresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StatisticsViewModel {
    private static final String TAG = StatisticsViewModel.class.getSimpleName();
    public ObservableField<Statistic> statisticObservable = new ObservableField<>();
    private Disposable disposable;
    private final StatisticsPresenter statisticsPresenter;

    public StatisticsViewModel(StatisticsPresenter statisticsPresenter, Disposable disposable) {
        this.disposable = disposable;
        this.statisticsPresenter = statisticsPresenter;
    }

    public void initStatisticalData() {
        IKmaScoreApi.getInstance.getStatistics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StatisticsResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "on subscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull StatisticsResult statisticsResult) {
                        if (statisticsResult != null && statisticsResult.getStatusCode() == 200) {
                            statisticObservable.set(statisticsResult.getData());
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
        statisticsPresenter.openUrlFromTvKitFooter();
    }

}
