package com.example.application.views;

import com.example.application.entity.User;
import com.example.application.service.IUserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@PageTitle("Registered Users")
@Route(value = "registered-users", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class RegisteredUsersView extends VerticalLayout {

    Grid<User> userGrid = new Grid<>(User.class, false);
    @Autowired
    private IUserService userService;


    public RegisteredUsersView() {
        userGrid.addColumn(User::getFirstName).setHeader("First Name");
        userGrid.addColumn(User::getLastName).setHeader("Last Name");
        userGrid.addColumn(User::getEmail).setHeader("Email");
        userGrid.addColumn(User::getPhoneNumber).setHeader("Phone Number");
        userGrid.addColumn(User::getCprNumber).setHeader("CPR Number");

        add(userGrid);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    @PostConstruct
    public void postConstructor() {
        userGrid.setItems(userService.findAll());
    }

}
