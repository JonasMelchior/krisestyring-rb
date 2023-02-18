package com.example.application;

import com.example.application.entity.CrisisEvent;
import com.example.application.entity.User;
import com.example.application.service.ICrisisEventService;
import com.example.application.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAvailableUser {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICrisisEventService crisisEventService;

    @Test
    public void testAvailableUserInsertion() {
        CrisisEvent crisisEvent = crisisEventService.findAll().get(0);
        List<User> registeredUsers = userService.findAll();

        for (int i = 0; i < registeredUsers.size(); i += 2) {
            Long id = registeredUsers.get(i).getId();
            if (crisisEvent.getAvailableVolunteers() != null) {
                crisisEvent.setAvailableVolunteers(crisisEvent.getAvailableVolunteers() + id + ";");
            }
            else {
                crisisEvent.setAvailableVolunteers(id + ";");
            }

        }

        crisisEventService.save(crisisEvent);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
