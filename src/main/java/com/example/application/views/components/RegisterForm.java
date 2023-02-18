package com.example.application.views.components;

import com.example.application.entity.User;
import com.example.application.service.IUserService;
import com.example.application.service.UserService;
import com.example.application.views.MainLayout;
import com.example.application.views.authentication.RegisteredView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


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
    ComboBox<String> registeredThroughField = new ComboBox<>("Registered Through");
    MultiSelectComboBox<String> competencesSelection = new MultiSelectComboBox<>("Competences");
    Checkbox physicalWorkCheckBox = new Checkbox("Physical Labor (Are you able to lift heavier objects?)");


    public RegisterForm(@Autowired UserService userService) {
        this.service = userService;

        competencesSelection.setItems("First Aid", "Construction", "Truck Driver", "Cook", "Strong");
        registeredThroughField.setItems("Røde Kors", "Roskilde Brandvæsen");
        Div phoneNumberAreaCode = new Div();
        phoneNumberAreaCode.setText("+45");
        phoneNumber.setPrefixComponent(phoneNumberAreaCode);
        Button registerButton = new Button("Register", register -> {
            service.save(new User(
                    firstName.getValue(),
                    lastName.getValue(),
                    email.getValue(),
                    "+45" + phoneNumber.getValue(),
                    cprNumber.getValue(),
                    registeredThroughField.getValue(),
                    competencesSelection.getValue().toString().substring(1, competencesSelection.getValue().toString().length() - 1).replaceAll("\\s+",""),
                    physicalWorkCheckBox.getValue()
            ));
            UI.getCurrent().navigate(RegisteredView.class);
        });

        add(
                firstName,
                lastName,
                email,
                phoneNumber,
                cprNumber,
                registeredThroughField,
                competencesSelection,
                physicalWorkCheckBox,
                registerButton
        );


    }
}
