package com.example.application.services;

import com.example.application.data.Task;
import com.example.application.data.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.application.data.TaskStepRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskStepRepository taskStepRepository;

    public TaskService(TaskRepository taskRepository, TaskStepRepository taskStepRepository) {
        this.taskRepository = taskRepository;
        this.taskStepRepository = taskStepRepository;
    }

    public void addTask(String title, String description, String priority, String date, String ownerLogin, List<String> steps) {
        if (title == null || title.isBlank()) {
            return;
        }

        Task task = new Task(title, description, priority, date, ownerLogin);

        for (String step : steps) {
            task.addStep(step);
        }

        taskRepository.save(task);
    }

    public List<Task> getTasks(String ownerLogin) {
        return taskRepository.findByOwnerLogin(ownerLogin);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void markDone(Long id) {

        taskRepository.findById(id).ifPresent(task -> {
            task.setDone(true);
            taskRepository.save(task);
        });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void setStepDone(Long stepId, boolean done) {
        taskStepRepository.findById(stepId).ifPresent(step -> {
            step.setDone(done);
            taskStepRepository.save(step);
        });
    }
}