package com.example.application.views;


import com.example.application.entity.User;
import com.example.application.service.ICrisisEventService;
import com.example.application.service.IUserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("Available Users")
@Route(value = "available-users", layout = MainLayout.class)
public class AvailableUsersView extends VerticalLayout {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICrisisEventService crisisEventService;

    Grid<User> userGrid = new Grid<>(User.class, false);
    GridListDataView<User> dataView;

    TextField searchField = new TextField();

    public AvailableUsersView() {
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

        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        add(searchField, userGrid);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }

    @PostConstruct
    public void postConstructor() {
        List<String> userIdStrings =  Arrays.stream(crisisEventService.findAll().get(0).getAvailableVolunteers().split(";")).toList();
        List<Long> userIds = new ArrayList<>();

        for (String id : userIdStrings) {
            userIds.add(Long.valueOf(id));
        }

        dataView = userGrid.setItems(userService.findAllById(userIds));
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
