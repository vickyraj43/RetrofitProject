package com.app.androidx_project.model;

import java.util.Arrays;

public class Topics {
    private String id ;
    private String type;
    private String name;
    private Questions[] questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Questions[] getQuestions() {
        return questions;
    }

    public void setQuestions(Questions[] questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Topics{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", questions=" + Arrays.toString(questions) +
                '}';
    }
}
