package com.example.kmascore.models;

public class MiniStudent {
    private String classInSchool;
    private String id;
    private String name;

    public MiniStudent(String classInSchool, String id, String name) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
