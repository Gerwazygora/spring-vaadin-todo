package com.example.application.views.login;
import com.example.application.services.UserService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Login")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class LoginView extends Composite<VerticalLayout> {

    private final UserService userService;

    public LoginView(UserService userService){
        this.userService = userService;
        H1 h1 = new H1();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("LOG IN");
        h1.setWidth("max-content");
        textField.setLabel("Login");
        textField.setWidth("min-content");
        textField2.setLabel("Password");
        textField2.setWidth("min-content");
        buttonPrimary.setText("LOG IN");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Register");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h1);
        getContent().add(textField);
        getContent().add(textField2);
        getContent().add(buttonPrimary);
        getContent().add(buttonPrimary2);

        buttonPrimary.addClickListener(event -> {
            boolean loggedIn = userService.login(
                    textField.getValue(),
                    textField2.getValue()
            );

            if (loggedIn) {
                Notification.show("Zalogowano");
                getUI().ifPresent(ui -> ui.navigate("my-view2"));
            } else {
                Notification.show("Błędny login lub hasło");
            }
        });

        buttonPrimary2.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("register"));
        });


    }
}
