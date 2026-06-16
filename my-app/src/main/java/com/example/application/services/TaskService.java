package com.example.application.services;

import com.example.application.data.Task;
import com.example.application.data.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String title, String description, String priority, String date, String ownerLogin) {
        if (title == null || title.isBlank()) {
            return;
        }

        taskRepository.save(new Task(title, description, priority, date, ownerLogin));
    }

    public List<Task> getTasks(String ownerLogin) {
        return taskRepository.findByOwnerLogin(ownerLogin);
    }
}