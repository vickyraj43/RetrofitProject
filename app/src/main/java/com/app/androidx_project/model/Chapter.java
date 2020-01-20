package com.app.androidx_project.model;

import java.util.Arrays;

public class Chapter {
    private int id ;
    private String type;
    private String name;
    private String questionimage;
    private String answerimage;
    private Topics[] topics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getQuestionimage() {
        return questionimage;
    }

    public void setQuestionimage(String questionimage) {
        this.questionimage = questionimage;
    }

    public String getAnswerimage() {
        return answerimage;
    }

    public void setAnswerimage(String answerimage) {
        this.answerimage = answerimage;
    }

    public Topics[] getTopics() {
        return topics;
    }

    public void setTopics(Topics[] topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", questionimage='" + questionimage + '\'' +
                ", answerimage='" + answerimage + '\'' +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }
}
