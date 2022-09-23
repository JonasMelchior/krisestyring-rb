package com.example.application.views.components;


import com.example.application.entity.CrisisEvent;
import com.example.application.entity.User;
import com.example.application.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CrisisEventForm extends FormLayout {
    /*
    * Backend
    * */
    private ICrisisEventService crisisEventService;
    private IUserService userService;

    /*
    * Form components
    * */
    TextField crisisName = new TextField("Crisis Name");
    TextField description = new TextField("Description");
    DateTimePicker meetingDate = new DateTimePicker("Meeting Date and Time");

    public CrisisEventForm(@Autowired CrisisEventService crisisEventService, @Autowired UserService userService) {
        this.crisisEventService = crisisEventService;
        this.userService = userService;

        Button registerEventButton = new Button("Send Event Details to all users in the system", event -> {
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
            List<User> users = userService.findAll();
            for (User user : users) {
                //crisisEventCommunicator.sendSms(String.valueOf(user.getPhoneNumber()));
                crisisEventCommunicator.sendEmail(user.getEmail(), user.getPhoneNumber());
            }
        });



        add(
                crisisName,
                description,
                meetingDate,
                registerEventButton
        );
    }

}
