package com.example.application.views.authentication;

import com.example.application.service.UserService;
import com.example.application.views.MainLayout;
import com.example.application.views.components.RegisterForm;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("RB | Registration")
@Route(value = "register")
public class RegisterView extends VerticalLayout {

    public RegisterView(@Autowired UserService userService) {
        RegisterForm registerForm = new RegisterForm(userService);

        HorizontalLayout registerLayout = new HorizontalLayout(registerForm);
        registerLayout.setPadding(true);
        registerLayout.setWidth("500px");

        add(new H1("Volunteer registration at Roskilde Brandvaesen"), registerLayout);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

    }
}
