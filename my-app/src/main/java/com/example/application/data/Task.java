package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.FetchType;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String ownerLogin;
    private String priority;
    private String date;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TaskStep> steps = new ArrayList<>();

    public Task() {
    }

    public Task(String title, String description, String priority, String date, String ownerLogin) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.ownerLogin = ownerLogin;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void addStep(String name) {
        steps.add(new TaskStep(name, this));
    }

    public List<TaskStep> getSteps() {
        return steps;
    }
}