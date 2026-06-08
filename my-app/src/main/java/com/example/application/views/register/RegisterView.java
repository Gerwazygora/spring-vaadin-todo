package com.example.application.views.register;
import com.example.application.services.UserService;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Register")
@Route("register")
@Menu(order = 1, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class RegisterView extends Composite<VerticalLayout> {

    private final UserService userService;

    public RegisterView(UserService userService){
        this.userService = userService;
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        h1.setText("Register");
        h1.setWidth("max-content");
        textField.setLabel("Login");
        textField.setWidth("min-content");
        textField2.setLabel("Password");
        textField2.setWidth("min-content");
        buttonPrimary.setText("REGISTER");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(textField);
        layoutColumn2.add(textField2);
        layoutColumn2.add(buttonPrimary);

        buttonPrimary.addClickListener(event -> {
            boolean registered = userService.register(
                    textField.getValue(),
                    textField2.getValue()
            );

            if (registered) {
                Notification.show("Konto utworzone");
                getUI().ifPresent(ui -> ui.navigate(""));
            } else {
                Notification.show("Login zajęty albo puste pola");
            }
        });


    }


}
