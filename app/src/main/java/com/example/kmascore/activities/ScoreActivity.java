package com.example.kmascore.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kmascore.R;
import com.example.kmascore.databinding.ActivityScoreBinding;
import com.example.kmascore.fragments.StatisticsFragment;
import com.example.kmascore.fragments.StudentFragment;
import com.example.kmascore.presenters.ScorePresenter;
import com.example.kmascore.viewmodels.ScoreViewModel;

public class ScoreActivity extends AppCompatActivity implements ScorePresenter {
    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ScoreViewModel scoreViewModel = new ScoreViewModel(this);
        // set viewModel
        binding.topBarScoreActivity.setScoreViewModel(scoreViewModel);

        // set started statistics fragment
        initStatisticsFragment();
    }

    private void initStatisticsFragment() {
        StatisticsFragment statisticsFragment = new StatisticsFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(binding.llForFragment.getId(), statisticsFragment);
        ft.commit();
    }

    @Override
    public void onNavigateUrl() {
        String url = getString(R.string.src_kma_score_web);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onSearchData() {
        Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
        //TODO:TEST
        StudentFragment studentFragment = new StudentFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(binding.llForFragment.getId(), studentFragment);
        ft.addToBackStack(studentFragment.getClass().getSimpleName());
        ft.commit();
    }
}
