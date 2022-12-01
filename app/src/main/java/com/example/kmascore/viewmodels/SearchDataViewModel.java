package com.example.kmascore.viewmodels;

import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.models.SearchResult;
import com.example.kmascore.presenters.SearchDataPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchDataViewModel {
    private static final String TAG = SearchDataViewModel.class.getSimpleName();
    private SearchDataPresenter searchDataPresenter;
    public ObservableField<String> searchData = new ObservableField<>();
    private Disposable disposable;

    // handle multiply search request from user
    private final PublishSubject<String> subjectSearchRequest = PublishSubject.create();
    private final @NonNull Disposable disposableSearchRequest = subjectSearchRequest
            .debounce(400, TimeUnit.MILLISECONDS)
            .filter(new Predicate<String>() {
                @Override
                public boolean test(String s) throws Throwable {
                    if (s.isEmpty()) {
                        return false;
                    } else {
                        return true;
                    }
                }
            })
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    Log.e(TAG, s);
                    makeCallApi(s);
                }
            });

    // constructor
    public SearchDataViewModel(SearchDataPresenter searchDataPresenter, Disposable disposable) {
        this.searchDataPresenter = searchDataPresenter;
        this.disposable = disposable;
    }

    public void onSearchEditTextChanged(CharSequence s, int start, int before, int count) {
        String text = s.toString().trim();

        // if text query not null
        if (!"".equals(text)) {
            subjectSearchRequest.onNext(text);
        }
    }

    private void makeCallApi(String text) {
        // call
        IKmaScoreApi.getInstance.search(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "on subscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull SearchResult searchResult) {
                        if (searchResult != null && searchResult.getStatusCode() == 200) {
                            // update UI
                            searchDataPresenter.showMiniStudentToUI(searchResult.getData());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "on error -> " + text);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "on complete");
                    }
                });
    }
}
