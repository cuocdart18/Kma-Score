package com.example.kmascore.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.kmascore.database.Converters;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "mini_student")
public class MiniStudent {
    @SerializedName("class")
    private String classInSchool;
    @PrimaryKey()
    @NonNull
    private String id;
    private String name;
    @TypeConverters({Converters.class})
    private Date dateModified;
    @Ignore
    private int index;

    public MiniStudent(String classInSchool, @NonNull String id, String name) {
        this.classInSchool = classInSchool;
        this.id = id;
        this.name = name;
    }

    public String getClassInSchool() {
        return classInSchool;
    }

    public void setClassInSchool(String classInSchool) {
        this.classInSchool = classInSchool;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
