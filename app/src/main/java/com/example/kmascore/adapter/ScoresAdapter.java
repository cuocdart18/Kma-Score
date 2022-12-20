package com.example.kmascore.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kmascore.databinding.ItemSubjectScoreBinding;
import com.example.kmascore.models.Score;

import java.util.List;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder> {
    private final IScoreCallback iScoreCallback;
    private List<Score> scores;
    private ItemSubjectScoreBinding binding;

    public ScoresAdapter(IScoreCallback iScoreCallback) {
        this.iScoreCallback = iScoreCallback;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setScores(List<Score> scores) {
        this.scores = scores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemSubjectScoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ScoresViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {
        Score score = scores.get(position);
        // position to define color of row
        score.setIndex(position);
        holder.binding.setScore(score);
        // set on click for item subject score
        holder.binding.getRoot().setOnClickListener(v -> iScoreCallback.openStatisticSubject(score.getSubject()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class ScoresViewHolder extends RecyclerView.ViewHolder {
        private final ItemSubjectScoreBinding binding;

        public ScoresViewHolder(@NonNull ItemSubjectScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
