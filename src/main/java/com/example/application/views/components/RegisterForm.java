package com.example.application.views.components;

import com.example.application.entity.User;
import com.example.application.service.IUserService;
import com.example.application.service.UserService;
import com.example.application.views.MainLayout;
import com.example.application.views.authentication.RegisteredView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


public class RegisterForm extends FormLayout {

    /*
    * Backend
    * */
    private IUserService service;

    /*
    * Form components
    * */
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    TextField phoneNumber = new TextField("Phone Number");
    NumberField cprNumber = new NumberField("CPR Number");


    public RegisterForm(@Autowired UserService userService) {
        this.service = userService;

        Div phoneNumberAreaCode = new Div();
        phoneNumberAreaCode.setText("+45");
        phoneNumber.setPrefixComponent(phoneNumberAreaCode);
        Button registerButton = new Button("Register", register -> {
            service.save(new User(
                    firstName.getValue(),
                    lastName.getValue(),
                    email.getValue(),
                    "+45" + phoneNumber.getValue(),
                    cprNumber.getValue()
            ));
            UI.getCurrent().navigate(RegisteredView.class);
        });

        add(
                firstName,
                lastName,
                email,
                phoneNumber,
                cprNumber,
                registerButton
        );


    }
}
