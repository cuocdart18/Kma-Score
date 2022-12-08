package com.example.kmascore.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.kmascore.models.MiniStudent;

@Database(entities = {MiniStudent.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MiniStudentDatabase extends RoomDatabase {

    private static final String DB_NAME = "mini_student.db";
    private static MiniStudentDatabase instance;

    public static synchronized MiniStudentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MiniStudentDatabase.class, DB_NAME)
                    .build();
        }
        return instance;
    }

    public abstract MiniStudentDAO miniStudentDAO();
}
