package com.example.application.views;

import com.example.application.entity.CrisisActivity;
import com.example.application.entity.User;
import com.example.application.service.CrisisEventService;
import com.example.application.service.ICrisisEventService;
import com.example.application.service.IUserService;
import com.example.application.service.UserService;
import com.example.application.views.components.CrisisEventForm;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("Crisis Tool")
@Route(value = "crisis-tool", layout = MainLayout.class)
public class CrisisToolView extends VerticalLayout {

    private ICrisisEventService crisisEventService;
    private IUserService userService;

    public CrisisToolView(@Autowired CrisisEventService crisisEventService, @Autowired UserService userService) {
        this.crisisEventService = crisisEventService;
        this.userService = userService;

        /*
         * For testing prototype
         * TODO - Hook up to database
         * */
        crisisActivities.add(new CrisisActivity(
                "Test 1",
                "Test responsible 1",
                "Test description 1"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 2",
                "Test responsible 2",
                "Test description 2"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 3",
                "Test responsible 3",
                "Test description 3"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 4",
                "Test responsible 4",
                "Test description 4"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 5",
                "Test responsible 5",
                "Test description 5"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 6",
                "Test responsible 6",
                "Test description 6"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 7",
                "Test responsible 7",
                "Test description 7"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 8",
                "Test responsible 8",
                "Test description 8"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 9",
                "Test responsible 9",
                "Test description 9"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 10",
                "Test responsible 10",
                "Test description 10"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 11",
                "Test responsible 11",
                "Test description 11"
        ));
        crisisActivities.add(new CrisisActivity(
                "Test 12",
                "Test responsible 12",
                "Test description 12"
        ));

        CrisisEventForm crisisEventForm = new CrisisEventForm(crisisEventService, userService);
        crisisEventForm.setVisible(false);
        crisisActivityForm.setVisible(false);
        Button registerCrisisEventButton = new Button("Crisis Event Form", event -> {
            crisisEventForm.setVisible(!crisisEventForm.isVisible());
        });

        add(registerCrisisEventButton, crisisEventForm, createMainLayout());

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private VerticalLayout createMainLayout() {
        VerticalLayout mainLayout = new VerticalLayout(
                createGroupingLayout()
                // TODO - add map layout of activities
                );


        return mainLayout;
    }


    private VerticalLayout createGroupingLayout() {
        VerticalLayout groupingLayout = new VerticalLayout(
                createAvailableUsersGrid(),
                createActivitiesLayoutWrapper()
                );
        groupingLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        return groupingLayout;
    }

    List<User> availableVolunteers = new ArrayList<>();
    private Grid<User> createAvailableUsersGrid() {
        Grid<User> userGrid = new Grid<>(User.class, false);

        userGrid.addColumn(User::getFirstName).setHeader("First Name");
        userGrid.addColumn(User::getLastName).setHeader("Last Name");
        userGrid.addColumn(User::getEmail).setHeader("Email");
        userGrid.addColumn(User::getPhoneNumber).setHeader("Phone Number");
        userGrid.addColumn(User::getCprNumber).setHeader("CPR Number");
        userGrid.addComponentColumn(user -> {
            Button addToActivityButton = new Button(new Icon(VaadinIcon.PLUS), add -> {
                /*
                * TODO: Add to activity in acitivities flexlayout
                * */
                createAddUserToActivityDialog(user).open();
            });

            return addToActivityButton;
        }).setHeader("Add User to activity");

        String availableVolunteersString = crisisEventService.findAll().get(0).getAvailableVolunteers();
        String[] availableUsersParsed = availableVolunteersString.substring(0, availableVolunteersString.length() - 1).split(";");

        List<Long> volunteerIds = new ArrayList<>(availableUsersParsed.length);
        for (String s : availableUsersParsed) {
            volunteerIds.add(Long.valueOf(s));
        }
        availableVolunteers = userService.findAllById(volunteerIds);
        userGrid.setItems(availableVolunteers);

        return userGrid;
    }

    Dialog createAddUserToActivityDialog(User user) {
        VerticalLayout addUserToActivityDialogLayout = new VerticalLayout();

        Dialog addUserToActivityDialog = new Dialog();

        for (int i = 0; i < crisisActivities.size(); i++) {
            int finalI = i;
            addUserToActivityDialogLayout.add(new Button("Activity " + crisisActivities.get(i).getName(), add -> {
                if (crisisActivities.get(finalI).getAssignedVolunteers() == null) {
                    crisisActivities.get(finalI).setAssignedVolunteers(user.getId() + ";");
                }
                else {
                    crisisActivities.get(finalI).setAssignedVolunteers(crisisActivities.get(finalI).getAssignedVolunteers() + user.getId() + ";");
                }
                updateActivityLayout();
                addUserToActivityDialog.close();
            }));
        }

        addUserToActivityDialog.add(addUserToActivityDialogLayout);

        return addUserToActivityDialog;
    }

    private void updateActivityLayout() {
        activitiesLayout.removeAll();
        for (CrisisActivity crisisActivity : crisisActivities) {
            VerticalLayout crisisActivityLayout = new VerticalLayout();

            Div name = new Div();
            name.setText("Activity Name: " + crisisActivity.getName());

            Div responsible = new Div();
            responsible.setText("Responsible: " + crisisActivity.getResponsible());

            Div description = new Div();
            description.setText("Description: " + crisisActivity.getDescription());

            VirtualList<User> virtualList = new VirtualList<>();

            String availableVolunteersString = crisisActivity.getAssignedVolunteers();
            System.out.println(availableVolunteersString);
            if (availableVolunteersString != null) {
                String[] availableUsersParsed = availableVolunteersString.substring(0, availableVolunteersString.length() - 1).split(";");

                List<Long> volunteerIds = new ArrayList<>(availableUsersParsed.length);
                for (String s : availableUsersParsed) {
                    volunteerIds.add(Long.valueOf(s));
                }

                List<User> activityVolunteers = userService.findAllById(volunteerIds);
                virtualList.setItems(activityVolunteers);
                crisisActivityLayout.add(name, responsible, description, new H5("Assigned Volunteers:"));
                for (User user : activityVolunteers) {
                    Div volunteer = new Div();
                    volunteer.setText(user.getFirstName() + " " + user.getLastName());
                    crisisActivityLayout.add(volunteer);
                }
            }
            else {
                crisisActivityLayout.add(name, responsible, description);
            }
            crisisActivityLayout.setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
            crisisActivityLayout.addClassName("layout-with-border-activity-component");


            activitiesLayout.setFlexBasis("300px", crisisActivityLayout);
            activitiesLayout.add(crisisActivityLayout);
        }
    }


    private VerticalLayout createActivitiesLayoutWrapper() {
        Button addActivityButton = new Button("Make new Activity");
        addActivityButton.addClickListener(add -> {
            crisisActivityForm.setVisible(!crisisActivityForm.isVisible());
        });
        VerticalLayout activitiesLayout = new VerticalLayout(addActivityButton, createCrisisActivityFormLayout(), createActivitiesLayout());
        activitiesLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        return activitiesLayout;
    }

    FormLayout crisisActivityForm = new FormLayout();
    List<CrisisActivity> crisisActivities = new ArrayList<>();
    FlexLayout activitiesLayout = new FlexLayout();

    private FormLayout createCrisisActivityFormLayout() {
        crisisActivityForm.setVisible(false);

        TextField name = new TextField("Activity Name");
        TextField responsible = new TextField("Responsible");
        TextField description = new TextField("Description");
        Button save = new Button("Save");
        Button close = new Button("Cancel");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
            CrisisActivity crisisActivity = new CrisisActivity(
                    name.getValue(),
                    responsible.getValue(),
                    description.getValue()
            );
            crisisActivities.add(crisisActivity);
            //TODO - save in database

        });

        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);


        close.addClickShortcut(Key.ESCAPE);
        close.addClickListener(closeEvent -> {
            crisisActivityForm.setVisible(false);
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout(save, close);

        crisisActivityForm.add(name, responsible, description, buttonsLayout);

        return crisisActivityForm;
    }

    private FlexLayout createActivitiesLayout() {
        activitiesLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        activitiesLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);




        for (CrisisActivity crisisActivity : crisisActivities) {
            Div name = new Div();
            name.setText("Activity Name: " + crisisActivity.getName());

            Div responsible = new Div();
            responsible.setText("Responsible: " + crisisActivity.getResponsible());

            Div description = new Div();
            description.setText("Description: " + crisisActivity.getDescription());

            VerticalLayout crisisActivityLayout = new VerticalLayout(name, responsible, description);
            crisisActivityLayout.setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
            crisisActivityLayout.addClassName("layout-with-border-activity-component");


            activitiesLayout.setFlexBasis("300px", crisisActivityLayout);
            activitiesLayout.add(crisisActivityLayout);
        }

        return activitiesLayout;
    }


}
