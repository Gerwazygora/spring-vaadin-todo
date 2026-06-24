package com.example.application.views.detaials;

import com.example.application.data.Task;
import com.example.application.services.TaskService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H2;

@PageTitle("Detaials")
@Route("my-view3/:taskId")
@Menu(order = 4, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class DetaialsView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    private final TaskService taskService;

    private final H1 title = new H1();
    private final Paragraph description = new Paragraph();
    private final Paragraph priority = new Paragraph();
    private final Paragraph date = new Paragraph();
    private final Button backButton = new Button("Back");
    private final Button deleteButton = new Button("Delete Task");
    private Task currentTask;
    private final Paragraph status = new Paragraph();
    private final H2 stepsTitle = new H2("Steps");
    private final VerticalLayout stepsLayout = new VerticalLayout();


    public DetaialsView(TaskService taskService) {
        this.taskService = taskService;

        backButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backButton.addClickListener(event ->
                getUI().ifPresent(ui -> ui.navigate("my-view2"))
        );
        Button doneButton = new Button("Mark Done");
        doneButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        deleteButton.addClickListener(event -> {
            if (currentTask != null) {
                taskService.deleteTask(currentTask.getId());
                Notification.show("Task deleted");
                getUI().ifPresent(ui -> ui.navigate("my-view2"));
            }
        });

        doneButton.addClickListener(event -> {
            if (currentTask != null) {
                taskService.markDone(currentTask.getId());
                Notification.show("Task marked as done");
                getUI().ifPresent(ui -> ui.navigate("my-view2"));
            }
        });

        getContent().add(
                title,
                description,
                priority,
                date,
                status,
                stepsTitle,
                stepsLayout,
                doneButton,
                deleteButton,
                backButton
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Long taskId = Long.valueOf(
                event.getRouteParameters()
                        .get("taskId")
                        .orElse("0")
        );

        taskService.getTaskById(taskId).ifPresentOrElse(task -> {
            currentTask = task;

            title.setText(task.getTitle());
            description.setText("Description: " + task.getDescription());
            priority.setText("Priority: " + task.getPriority());
            date.setText("Date: " + task.getDate());
            status.setText("Status: " + (task.isDone() ? "Done" : "Not done"));

            stepsLayout.removeAll();

            task.getSteps().forEach(step -> {
                Checkbox checkbox = new Checkbox(step.getName());
                checkbox.setValue(step.isDone());

                checkbox.addValueChangeListener(changeEvent -> {
                    taskService.setStepDone(step.getId(), changeEvent.getValue());
                });

                stepsLayout.add(checkbox);
            });

        }, () -> {
            title.setText("Task not found");
            description.setText("");
            priority.setText("");
            date.setText("");
            status.setText("");
            stepsLayout.removeAll();
        });
    }


}