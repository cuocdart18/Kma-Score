package com.example.kmascore.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kmascore.adapter.IMiniStudentCallback;
import com.example.kmascore.adapter.SearchDataAdapter;
import com.example.kmascore.databinding.DialogSearchBinding;
import com.example.kmascore.models.MiniStudent;
import com.example.kmascore.presenters.SearchDataPresenter;
import com.example.kmascore.viewmodels.SearchDataViewModel;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class SearchDataDialogFragment extends DialogFragment implements SearchDataPresenter, IMiniStudentCallback {
    private ISendDataToActivity iSendDataToActivity;
    private DialogSearchBinding binding;
    private SearchDataViewModel searchDataViewModel;

    private SearchDataAdapter searchDataAdapter = new SearchDataAdapter(this);
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

    private Disposable disposable;

    public SearchDataDialogFragment(ISendDataToActivity iSendDataToActivity) {
        this.iSendDataToActivity = iSendDataToActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogSearchBinding.inflate(inflater, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setScaleUI();
        setRecyclerViewProperties();

        //setup viewModel
        searchDataViewModel = new SearchDataViewModel(this, disposable);
        binding.setSearchDataVM(searchDataViewModel);

        searchDataViewModel.showRecentSearchHistory(getContext());
    }

    private void setScaleUI() {
        // Set the width of the dialog proportional
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.80), (int) (size.y * 0.60));
        window.setGravity(Gravity.CENTER);
    }

    private void setRecyclerViewProperties() {
        // set recycler view
        binding.rvSearchQuery.setLayoutManager(linearLayoutManager);
        binding.rvSearchQuery.setFocusable(false);
        binding.rvSearchQuery.setNestedScrollingEnabled(false);
    }

    @Override
    public void showMiniStudentToUI(List<MiniStudent> miniStudents) {
        searchDataAdapter.setMiniStudents(miniStudents);
        binding.rvSearchQuery.setAdapter(searchDataAdapter);
    }

    @Override
    public void onClickItemInSearchDataDialog(MiniStudent miniStudent) {
        // add student into recent db
        searchDataViewModel.insertMiniStudentToDb(getContext(), miniStudent);
        // query data
        iSendDataToActivity.sendData(miniStudent);
        this.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public interface ISendDataToActivity {
        void sendData(MiniStudent miniStudent);
    }
}
