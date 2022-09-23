package com.example.application.views.authentication;

import com.example.application.entity.CrisisEvent;
import com.example.application.entity.User;
import com.example.application.service.ICrisisEventService;
import com.example.application.service.IUserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Route(value = "sign-up")
public class SignUpForCrisisEventView extends Composite implements BeforeEnterObserver {

    @Autowired
    private ICrisisEventService crisisEventService;
    @Autowired
    private IUserService userService;
    private VerticalLayout layout;

    public SignUpForCrisisEventView() {}


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Map<String, List<String>> params = beforeEnterEvent.getLocation().getQueryParameters().getParameters();
        String phoneNumberParam = params.get("phone-number").get(0);

        List<User> users = userService.findAll();

        boolean userExists = false;
        User user = null;
        for (User tmpUser : users) {
            String param1 = phoneNumberParam.replace("+", "");
            String testingPn = param1.trim();
            String actualPn = tmpUser.getPhoneNumber().replace("+", "");
            if (testingPn.equals(actualPn)) {
                user = tmpUser;
                userExists = true;
            }
        }

        if (userExists) {
            CrisisEvent crisisEvent = crisisEventService.findAll().get(0);
            StringBuilder availableVolunteers = new StringBuilder();
            if (crisisEvent.getAvailableVolunteers() != null) {
                availableVolunteers = new StringBuilder(crisisEvent.getAvailableVolunteers());
            }
            availableVolunteers.append(user.getId()).append(";");
            layout.add(new H1("Successfully signed up for volunteering"));
            crisisEvent.setAvailableVolunteers(availableVolunteers.toString());
            crisisEventService.save(crisisEvent);
        }
        else {
            layout.add(new H1("An error occured"), new H2(phoneNumberParam), new H2(String.valueOf(phoneNumberParam.length())), new H2(String.valueOf(users.get(0).getPhoneNumber().length())));
        }



    }

    @Override
    protected Component initContent() {
        layout = new VerticalLayout();
        return layout;
    }
}
