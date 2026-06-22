package com.example.application.data;

import jakarta.persistence.*;

@Entity
public class TaskStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean done;

    @ManyToOne
    private Task task;

    public TaskStep() {
    }

    public TaskStep(String name, Task task) {
        this.name = name;
        this.task = task;
        this.done = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public Task getTask() {
        return task;
    }
}