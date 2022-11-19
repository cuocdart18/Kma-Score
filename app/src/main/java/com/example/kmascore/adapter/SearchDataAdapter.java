package com.example.kmascore.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kmascore.databinding.ItemSearchDataBinding;
import com.example.kmascore.models.MiniStudent;

import java.util.List;

public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.SearchDataViewHolder> {
    private List<MiniStudent> miniStudents;
    private ItemSearchDataBinding binding;
    private IMiniStudentCallback iMiniStudentCallback;

    public SearchDataAdapter(IMiniStudentCallback iMiniStudentCallback) {
        this.iMiniStudentCallback = iMiniStudentCallback;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMiniStudents(List<MiniStudent> miniStudents) {
        this.miniStudents = miniStudents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemSearchDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDataViewHolder holder, int position) {
        MiniStudent miniStudent = miniStudents.get(position);
        miniStudent.setIndex(position);
        binding.setMiniStudent(miniStudent);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iMiniStudentCallback.onClickItemInSearchDataDialog(miniStudent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return miniStudents.size();
    }

    public static class SearchDataViewHolder extends RecyclerView.ViewHolder {

        public SearchDataViewHolder(@NonNull ItemSearchDataBinding binding) {
            super(binding.getRoot());
        }
    }
}
