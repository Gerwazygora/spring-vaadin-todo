package com.example.application.views.addtask;

import com.example.application.data.SamplePerson;
import com.example.application.services.SamplePersonService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.example.application.services.TaskService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("AddTask")
@Route("my-view4")
@Menu(order = 3, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class AddTaskView extends Composite<VerticalLayout> {
    private final TaskService taskService;

    public AddTaskView(TaskService taskService) {
        this.taskService = taskService;
        H1 h1 = new H1();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Grid basicGrid = new Grid(SamplePerson.class);
        VerticalLayout layoutColumn2 = new VerticalLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        ComboBox<String> comboBox = new ComboBox<>();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Add Task");
        h1.setWidth("max-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        textField.setLabel("Name");
        textField.setWidth("min-content");
        textField2.setLabel("Description");
        textField2.setWidth("min-content");
        comboBox.setLabel("Priority");
        comboBox.setWidth("min-content");
        comboBox.setItems("Low", "Medium", "High");
        textField3.setLabel("Date");
        textField3.setWidth("min-content");
        textField4.setLabel("Add step");
        textField4.setWidth("min-content");
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Add Step");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Save Task");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h1);
        getContent().add(layoutRow);
        layoutRow.add(basicGrid);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(textField2);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(textField3);
        layoutColumn2.add(textField4);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(buttonPrimary);
        layoutRow2.add(buttonPrimary2);

        buttonPrimary.addClickListener(event -> {
            Notification.show("Kroki dodamy później");
        });

        buttonPrimary2.addClickListener(event -> {
            String user = (String) VaadinSession.getCurrent().getAttribute("user");

            if (user == null) {
                getUI().ifPresent(ui -> ui.navigate(""));
                return;
            }

            taskService.addTask(
                    textField.getValue(),
                    textField2.getValue(),
                    comboBox.getValue() == null ? "" : comboBox.getValue().toString(),
                    textField3.getValue(),
                    user
            );

            Notification.show("Zadanie dodane");
            getUI().ifPresent(ui -> ui.navigate("my-view2"));
        });
    }






}
