package com.example.kmascore.viewmodels;

import android.text.Editable;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.models.SearchResult;
import com.example.kmascore.presenters.SearchDataPresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchDataViewModel {
    private static final String TAG = SearchDataViewModel.class.getSimpleName();
    private SearchDataPresenter searchDataPresenter;
    public ObservableField<String> searchData = new ObservableField<>();
    private Disposable disposable;

    public SearchDataViewModel(SearchDataPresenter searchDataPresenter, Disposable disposable) {
        this.searchDataPresenter = searchDataPresenter;
        this.disposable = disposable;
    }

    synchronized public void onAfterSearchEditTextChanged(Editable editable) {
        String text = editable.toString();
        if (!"".equals(text)) {
            Log.e(TAG, text);

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

}
