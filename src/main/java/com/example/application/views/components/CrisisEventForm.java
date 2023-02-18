package com.example.application.views.components;


import com.example.application.entity.CrisisEvent;
import com.example.application.entity.User;
import com.example.application.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CrisisEventForm extends FormLayout {
    /*
    * Backend
    * */
    private ICrisisEventService crisisEventService;

    /*
    * Form components
    * */
    TextField crisisName = new TextField("Crisis Name");
    TextField description = new TextField("Description");
    DateTimePicker meetingDate = new DateTimePicker("Meeting Date and Time");
    List<User> selectedUsers = new ArrayList<>();

    public CrisisEventForm(@Autowired CrisisEventService crisisEventService) {
        this.crisisEventService = crisisEventService;

        Button registerEventButton = new Button("Send Event Details to selected users", new Icon(VaadinIcon.BELL));
        registerEventButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        registerEventButton.addClickListener(event -> {
            crisisEventService.delete();
            crisisEventService.save(new CrisisEvent(
                   crisisName.getValue(),
                   description.getValue(),
                   meetingDate.getValue()
           ));
            /*
             * TODO: send SMS to all users
             * */
            CrisisEventCommunicator crisisEventCommunicator = new CrisisEventCommunicator(
                    crisisName.getValue(),
                    description.getValue(),
                    meetingDate.getValue());

            int notifiedCounter = 0;

            for (User user : selectedUsers) {
                if (crisisEventCommunicator.sendSms(String.valueOf(user.getPhoneNumber())) || crisisEventCommunicator.sendEmail(user.getEmail(), user.getPhoneNumber())) {
                    notifiedCounter++;
                }
            }

            Notification notification = Notification.show("Successfully notified " + notifiedCounter + " users in the system");
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setDuration(30000);

        });



        add(
                crisisName,
                description,
                meetingDate,
                registerEventButton
        );

        //addClassName("crisis-event-layout");
    }


    public void setSelectedUsers(List<User> users) {
        this.selectedUsers = users;
    }
}
