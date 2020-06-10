package com.example.todo;

public class Tasks {
    String id;
    String tasks;

    public Tasks(String id,String tasks) {
        this.tasks = tasks;
        this.id = id;
    }

    public Tasks(long javob, String task) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
}
