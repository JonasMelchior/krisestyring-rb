package com.example.application.views.authentication;


import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("RB | Registered")
@Route(value = "registered")
public class RegisteredView extends VerticalLayout {

    public RegisteredView() {


        add(
                new H1("Congratulations - you are now registered as a volunteer for Roskilde Brandvaesen !"),
                new H2("You can safely leave this page")
        );
    }
}
