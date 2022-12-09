package com.example.kmascore.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.kmascore.models.MiniStudent;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MiniStudentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertStudent(MiniStudent miniStudent);

    @Query("SELECT * FROM mini_student ORDER BY dateModified DESC LIMIT 20")
    Single<List<MiniStudent>> getRecentHistorySearch();
}