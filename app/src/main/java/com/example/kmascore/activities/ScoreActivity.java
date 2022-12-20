package com.example.kmascore.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.kmascore.R;
import com.example.kmascore.databinding.ActivityScoreBinding;
import com.example.kmascore.fragments.SearchDataDialogFragment;
import com.example.kmascore.fragments.StatisticsFragment;
import com.example.kmascore.fragments.StudentFragment;
import com.example.kmascore.models.MiniStudent;
import com.example.kmascore.presenters.ScorePresenter;
import com.example.kmascore.viewmodels.ScoreViewModel;

import java.util.List;
import java.util.Objects;

public class ScoreActivity extends AppCompatActivity implements ScorePresenter, SearchDataDialogFragment.ISendDataToActivity {
    private static final String TAG = ScoreActivity.class.getSimpleName();
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
        String url = getString(R.string.src_kma_score_mobile);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onSearchData() {
        // open dialog
        SearchDataDialogFragment searchDataDialog = new SearchDataDialogFragment(this);
        searchDataDialog.show(getSupportFragmentManager(), "search_data_dialog");
    }

    @Override
    public void sendData(MiniStudent miniStudent) {
        // open statistics of student
        StudentFragment studentFragment = new StudentFragment(miniStudent);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        List<Fragment> fragments = fm.getFragments();
        if (Objects.equals(fragments.get(fragments.size() - 2).getTag(), miniStudent.getId())) {
            // request student has the same id with current student fragment
            Log.d(TAG, "student " + miniStudent.getId() + " is exist");
        } else {
            // unique id
            ft.add(binding.llForFragment.getId(), studentFragment, miniStudent.getId());
            ft.addToBackStack(StudentFragment.class.getSimpleName());
            ft.commit();
            Log.d(TAG, "add " + miniStudent.getId() + " successfully");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
