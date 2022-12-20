package com.example.kmascore.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kmascore.R;
import com.example.kmascore.adapter.IScoreCallback;
import com.example.kmascore.adapter.ScoresAdapter;
import com.example.kmascore.databinding.FragmentStudentBinding;
import com.example.kmascore.models.MiniStudent;
import com.example.kmascore.models.StatisticSubject;
import com.example.kmascore.models.Student;
import com.example.kmascore.models.Subject;
import com.example.kmascore.presenters.StudentScoresPresenter;
import com.example.kmascore.viewmodels.StudentScoreViewModel;

import io.reactivex.rxjava3.disposables.Disposable;

public class StudentFragment extends Fragment
        implements StudentScoresPresenter, IScoreCallback {
    private static final String TAG = StudentFragment.class.getSimpleName();
    private FragmentStudentBinding binding;
    private StudentScoreViewModel studentScoreViewModel;
    ScoresAdapter scoresAdapter;
    private final MiniStudent miniStudent;
    private Disposable disposable;

    public StudentFragment(MiniStudent miniStudent) {
        this.miniStudent = miniStudent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentScoreViewModel = new StudentScoreViewModel(disposable, this);
        binding.setStudentScoreVM(studentScoreViewModel);

        // init Data
        studentScoreViewModel.initStudentData(miniStudent.getId());
    }

    @Override
    public void bindingStudentData(Student student) {
        binding.setStudent(student);
        scoresAdapter = new ScoresAdapter(this);
        scoresAdapter.setScores(student.getScores());

        // update list to UI
        binding.rvScores.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvScores.setFocusable(false);
        binding.rvScores.setNestedScrollingEnabled(false);
        binding.rvScores.setAdapter(scoresAdapter);
    }

    @Override
    public void openStatisticSubject(Subject subject) {
        studentScoreViewModel.openStatisticSubject(subject);
    }

    @Override
    public void openStatisticSubject(StatisticSubject statisticSubject) {
        Log.d(TAG, "failed=" + statisticSubject.getFailedStudents());
        Log.d(TAG, "passed=" + statisticSubject.getPassedStudents());
        Toast.makeText(getContext(),
                "Failed students: " + statisticSubject.getFailedStudents()
                        + "\n"
                        + "Passed students: " + statisticSubject.getPassedStudents(),
                Toast.LENGTH_LONG).show();
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
