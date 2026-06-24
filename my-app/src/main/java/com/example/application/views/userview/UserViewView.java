package com.example.application.views.userview;

import com.example.application.data.SamplePerson;
import com.example.application.services.SamplePersonService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.server.VaadinSession;
import com.example.application.data.Task;
import com.example.application.services.TaskService;
import com.example.application.services.Broadcaster;
import com.vaadin.flow.component.UI;
import java.util.function.Consumer;

@PageTitle("UserView")
@Route("my-view2")
@Menu(order = 2, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class UserViewView extends Composite<VerticalLayout> {
    private final TaskService taskService;

    public UserViewView(TaskService taskService) {
        this.taskService = taskService;
        String user =
                (String) VaadinSession
                        .getCurrent()
                        .getAttribute("user");

        if (user == null) {
            getUI().ifPresent(ui -> ui.navigate(""));
            return;
        }
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        H1 userInfo = new H1("Witaj " + user);
        Button buttonPrimary = new Button();
        Grid<Task> basicGrid = new Grid<>();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h1.setText("List of Tasks");
        h1.setWidth("max-content");
        buttonPrimary.setText("Add Task");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        basicGrid.addColumn(Task::getTitle).setHeader("Name");
        basicGrid.addColumn(Task::getPriority).setHeader("Priority");
        basicGrid.addColumn(Task::getDate).setHeader("Date");
        basicGrid.addColumn(task -> task.isDone() ? "Done" : "Not done")
                .setHeader("Status");
        basicGrid.setItems(taskService.getTasks(user));

        UI currentUi = UI.getCurrent();

        Consumer<String> listener = message -> {
            if (message.equals("tasks-updated")) {
                currentUi.access(() -> {
                    basicGrid.setItems(taskService.getTasks(user));
                });
            }
        };

        Broadcaster.register(listener);

        addDetachListener(event -> Broadcaster.unregister(listener));
        basicGrid.addItemClickListener(event -> {
            Task task = event.getItem();

            getUI().ifPresent(ui ->
                    ui.navigate("my-view3/" + task.getId()));
        });
        getContent().add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(userInfo);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(basicGrid);

        buttonPrimary.addClickListener(event ->
                getUI().ifPresent(ui -> ui.navigate("my-view4"))
        );
    }


}
