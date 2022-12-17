package com.example.kmascore.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableField;

import com.example.kmascore.api_service.IKmaScoreApi;
import com.example.kmascore.database.MiniStudentDatabase;
import com.example.kmascore.models.MiniStudent;
import com.example.kmascore.models.SearchResult;
import com.example.kmascore.presenters.SearchDataPresenter;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchDataViewModel {
    private static final String TAG = SearchDataViewModel.class.getSimpleName();
    private final SearchDataPresenter searchDataPresenter;
    public ObservableField<String> searchData = new ObservableField<>();
    public ObservableField<Boolean> isUserTyped = new ObservableField<>();
    private Disposable disposable;

    // handle multiply search request from user
    private final PublishSubject<String> subjectSearchRequest = PublishSubject.create();
    private final @NonNull Disposable disposableSearchRequest = subjectSearchRequest
            .debounce(400, TimeUnit.MILLISECONDS)
            .filter(s -> !s.isEmpty())
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
                Log.e(TAG, s);
                makeCallApi(s);
            });

    // constructor
    public SearchDataViewModel(SearchDataPresenter searchDataPresenter, Disposable disposable) {
        this.searchDataPresenter = searchDataPresenter;
        this.disposable = disposable;

        //set textView searchHistory or resultHistory
        isUserTyped.set(false);
    }

    public void onSearchEditTextChanged(CharSequence s, int start, int before, int count) {
        String text = s.toString().trim();

        // if text query not null
        if (!"".equals(text)) {
            isUserTyped.set(true);
            subjectSearchRequest.onNext(text);
        }
    }

    // call student data API
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

    public void showRecentSearchHistory(Context context) {
        getListMiniStudentFromDatabase(context);

        Log.i(TAG, "show recent history");
    }

    // get list from database
    private void getListMiniStudentFromDatabase(Context context) {
        MiniStudentDatabase.getInstance(context).miniStudentDAO().getRecentHistorySearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MiniStudent>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "request db on subscribe");
                    }

                    @Override
                    public void onSuccess(@NonNull List<MiniStudent> miniStudents) {
                        if (miniStudents != null) {
                            searchDataPresenter.showMiniStudentToUI(miniStudents);
                        }

                        Log.i(TAG, "request db on success");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "request db on error ->> " + e.getMessage());
                    }
                });
    }

    public void insertMiniStudentToDb(Context context, MiniStudent miniStudent) {
        //set date modified and save in database
        miniStudent.setDateModified(Calendar.getInstance().getTime());
        saveMiniStudentsIntoDatabase(context, miniStudent);

        Log.i(TAG, "insert " + miniStudent);
    }

    //save data to database
    private void saveMiniStudentsIntoDatabase(Context context, MiniStudent miniStudent) {
        MiniStudentDatabase.getInstance(context).miniStudentDAO().insertStudent(miniStudent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG, "insert on subscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "insert on completed");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG, "insert on error --> " + e);
                    }
                });
    }
}
