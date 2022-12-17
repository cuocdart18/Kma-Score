package com.example.kmascore.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kmascore.R;
import com.example.kmascore.databinding.FragmentStatisticsBinding;
import com.example.kmascore.presenters.StatisticsPresenter;
import com.example.kmascore.viewmodels.StatisticsViewModel;

import io.reactivex.rxjava3.disposables.Disposable;

public class StatisticsFragment extends Fragment implements StatisticsPresenter {
    private FragmentStatisticsBinding binding;
    private StatisticsViewModel statisticsViewModel;
    private Disposable disposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statisticsViewModel = new StatisticsViewModel(this, disposable);
        binding.setStatisticsViewModel(statisticsViewModel);

        // init data
        statisticsViewModel.initStatisticalData();
    }

    @Override
    public void openUrlFromTvKitFooter() {
        String url = getString(R.string.link_gr_kit);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
