package com.example.kmascore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kmascore.databinding.FragmentStatisticsBinding;
import com.example.kmascore.viewmodels.StatisticsViewModel;

import io.reactivex.rxjava3.disposables.Disposable;

public class StatisticsFragment extends Fragment {
    private FragmentStatisticsBinding binding;
    private StatisticsViewModel statisticsViewModel;
    private Disposable disposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statisticsViewModel = new StatisticsViewModel(disposable);
        binding.setStatisticsViewModel(statisticsViewModel);

        // init data
        statisticsViewModel.initStatisticalData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
