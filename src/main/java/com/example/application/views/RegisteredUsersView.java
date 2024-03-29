package com.example.application.views;

import com.example.application.entity.User;
import com.example.application.service.CrisisEventService;
import com.example.application.service.ICrisisEventService;
import com.example.application.service.IUserService;
import com.example.application.service.UserService;
import com.example.application.views.components.CrisisEventForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.Rendering;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.*;

@PageTitle("Registered Users")
@Route(value = "registered-users", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class RegisteredUsersView extends VerticalLayout {

    Grid<User> userGrid = new Grid<>(User.class, false);
    @Autowired
    private IUserService userService;
    GridListDataView<User> dataView;

    TextField searchField = new TextField();


    public RegisteredUsersView(@Autowired CrisisEventService crisisEventService) {
        CrisisEventForm crisisEventForm = new CrisisEventForm(crisisEventService);
        crisisEventForm.setVisible(false);
        Button registerCrisisEventButton = new Button("Show Crisis Event Form", new Icon(VaadinIcon.ARROW_DOWN));
        registerCrisisEventButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerCrisisEventButton.addClickListener(event -> {
            crisisEventForm.setVisible(!crisisEventForm.isVisible());
        });



        userGrid.addColumn(User::getFirstName).setHeader("First Name").setSortable(true);
        userGrid.addColumn(User::getLastName).setHeader("Last Name").setSortable(true);
        userGrid.addComponentColumn( event -> {
            if (event.isPhysicalWork()) {
                return new Icon(VaadinIcon.CHECK_CIRCLE_O);
            }
            else {
                return new Icon(VaadinIcon.MINUS);
            }
        }).setHeader("Physical Labor").setSortable(true);
        userGrid.addColumn(User::getEmail).setHeader("Email").setSortable(true);
        userGrid.addColumn(User::getPhoneNumber).setHeader("Phone Number").setSortable(true);
        userGrid.addColumn(User::getCprNumber).setHeader("CPR Number").setSortable(true);
        userGrid.addColumn(User::getRegisteredThrough).setHeader("Registered Through").setSortable(true);
        userGrid.addColumn(User::getCompetences).setHeader("Competences").setSortable(true);
        userGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        userGrid.addSelectionListener( selection -> {
            crisisEventForm.setSelectedUsers(selection.getAllSelectedItems().stream().toList());
        });

        userGrid.setHeight("500px");


        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        HorizontalLayout crisisEventLayout = new HorizontalLayout(crisisEventForm);
        crisisEventLayout.setPadding(true);

        add(registerCrisisEventButton, crisisEventLayout, searchField, userGrid);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }

    @PostConstruct
    public void postConstructor() {
        dataView = userGrid.setItems(userService.findAll());
        searchField.addValueChangeListener(e -> dataView.refreshAll());

        dataView.addFilter(person -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty())
                return true;

            boolean matchesFirstName = matchesTerm(person.getFirstName(),
                    searchTerm);
            boolean matchesLastName = matchesTerm(person.getLastName(),
                    searchTerm);
            boolean matchesEmail = matchesTerm(person.getEmail(), searchTerm);
            boolean matchesPhoneNumber = matchesTerm(person.getPhoneNumber(),
                    searchTerm);
            boolean matchesCprNumber = matchesTerm(String.valueOf(person.getCprNumber()),
                    searchTerm);
            boolean matchesRegisteredThrough = matchesTerm(person.getRegisteredThrough(),
                    searchTerm);
            boolean matchesCompetences = matchesTerm(person.getCompetences(),
                    searchTerm);

            return matchesFirstName || matchesLastName || matchesEmail || matchesPhoneNumber || matchesCprNumber || matchesRegisteredThrough || matchesCompetences;
        });
    }

}
