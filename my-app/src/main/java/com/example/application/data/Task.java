package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}