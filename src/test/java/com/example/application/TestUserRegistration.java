package com.example.application;

import com.example.application.entity.User;
import com.example.application.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRegistration {

    @Autowired
    private IUserService service;

    @Test
    public void testUSerInsertion() {


        List<User> users = new ArrayList<>();

        users.add(new User(
                "Jonas",
                "Jensen",
                "s205129@student.dtu.dk",
                "+4523424285",
                2206992045D,
                "Røde Kors",
                "First Aid,Strong",
                true
        ));
        users.add(new User(
                "Bruce",
                "Lee",
                "BruceLee@gmail.com",
                "+4509572371",
                0987471654D,
                "Roskilde Brandvæsen",
                "Strong",
                true
        ));
        users.add(new User(
                "Morgan",
                "Pierce",
                "MorganPierce@gmail.com",
                "+4509796543",
                0978109825D,
                "Røde Kors",
                "",
                false
        ));
        users.add(new User(
                "Jonas",
                "Jensen",
                "jonasjensen04@gmail.com",
                "+4523424285",
                2206992045D,
                "Røde Kors",
                "First Aid,Strong",
                true
        ));
        users.add(new User(
                "Floyd",
                "Mayweather",
                "FloydMayWeather@gmail.com",
                "+4546176456",
                0989764765D,
                "Røde Kors",
                "First Aid, Strong,Truck Driver",
                false
        ));
        users.add(new User(
                "Karl",
                "Marx",
                "KarlMarx@gmail.com",
                "+4509582764",
                0978905671D,
                "Røde Kors",
                "Truck Driver",
                false
        ));
        users.add(new User(
                "Barack",
                "Obama",
                "BarackObama@gmail.com",
                "+4569213765",
                6087146752D,
                "Roskilde Brandvæsen",
                "First Aid,Strong",
                true
        ));
        users.add(new User(
                "Bernie",
                "Sanders",
                "BernieSanders@gmail.com",
                "+4595467812",
                4136786312D,
                "Røde Kors",
                "First Aid",
                true
        ));
        users.add(new User(
                "Random Firstname",
                "Random Lastname",
                "Random@gmail.com",
                "+4509785642",
                0767543219D,
                "Roskilde Brandvæsen",
                "Truck Driver",
                false
        ));
        users.add(new User(
                "Kim",
                "Jensen",
                "KimJensen@gmail.com",
                "+4559632147",
                7898521458D,
                "Røde Kors",
                "Truck Driver",
                true
        ));
        users.add(new User(
                "Timo",
                "Sørensen",
                "TimeSørensen@gmail.com",
                "+4578923784",
                1158752569D,
                "Roskilde Brandvæsen",
                "Strong",
                false
        ));
        users.add(new User(
                "Lars",
                "Larsen",
                "LarLarsen@gmail.com",
                "+4557821459",
                2451789360D,
                "Roskilde Brandvæsen",
                "First Aid",
                false
        ));
        users.add(new User(
                "Tina",
                "Jensen",
                "TineJensen@gmail.com",
                "+4559874580",
                7895623850D,
                "Røde Kors",
                "",
                true
        ));
        users.add(new User(
                "Jens",
                "Olesen",
                "JensOlesen@gmail.com",
                "+455780692",
                5780659863D,
                "Røde Kors",
                "Strong",
                true
        ));
        users.add(new User(
                "Heino",
                "Jensen",
                "HeinoJensen@gmail.com",
                "+4577905863",
                8059637208D,
                "Røde Kors",
                "First Aid",
                true
        ));
        users.add(new User(
                "Iver",
                "Jensen",
                "IverJensen@gmail.com",
                "+45859213568",
                5871502968D,
                "Røde Kors",
                "",
                false
        ));
        users.add(new User(
                "Matias",
                "Karstensen",
                "MatiasKarstensen@gmail.com",
                "+4580526892",
                2580245792D,
                "Roskilde Brandvæsen",
                "Truck Driver",
                true
        ));
        users.add(new User(
                "Bob",
                "Hope",
                "horses@gmail.com",
                "+4509346642",
                7584757575D,
                "Roskilde Brandvæsen",
                "Social Worker",
                true
        ));

        users.add(new User(
                "Sallem",
                "Tennis",
                "geese@gmail.com",
                "+45426242",
                8947487642D,
                "Red Cross",
                "Truck Driver",
                true
        ));


        users.add(new User(
                "Lady",
                "Hansen",
                "stuffandducks@gmail.com",
                "+4504574242",
                9564759364D,
                "Roskilde Brandvæsen",
                "Social Worker",
                false
        ));

        users.add(new User(
                "Random Firstname",
                "Random Lastname",
                "Random@gmail.com",
                "+4509785642",
                0767543219D,
                "Roskilde Brandvæsen",
                "Truck Driver",
                false
        ));

        users.add(new User(
                "Tracey",
                "Jermmon",
                "wearehere@gmail.com",
                "+554994873",
                00166455768D,
                "Roskilde Brandvæsen",
                "Diver, Digger",
                false
        ));

        users.add(new User(
                "Random Firstname",
                "Random Lastname",
                "Random@gmail.com",
                "+4509785642",
                0767543219D,
                "Roskilde Brandvæsen",
                "Truck Driver",
                false
        ));

        users.add(new User(
                "Steve",
                "Harris",
                "jaajaeu@gmail.com",
                "+4399878896",
                5647464746D,
                "Roskilde Brandvæsen",
                "Medic",
                false
        ));

        users.add(new User(
                "Hans",
                "Gorbattov",
                "sllenndowslie@gmail.com",
                "+599698935883",
                7746666446D,
                "Roskilde Brandvæsen",
                "Driver, Medic",
                true
        ));

        users.add(new User(
                "Penelope",
                "Prennecsko",
                "charlotte334@gmail.com",
                "+989876673",
                774666354324D,
                "Red Cross",
                "Medic, engineer",
                true
        ));

        users.add(new User(
                "Lemmy",
                "Kilmister",
                "motorhead@gmail.com",
                "+456666677",
                7824657654D,
                "Roskilde Brandvæsen",
                "Bassist, Driver",
                true
        ));
        service.saveAll(users);
    }

    @Test
    public void getAllUsers() {
        List<User> users = service.findAll();

        for (User user : users) {
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getEmail());
            System.out.println(user.getPhoneNumber());
            System.out.println(user.getCprNumber());
            System.out.println(user.getRegisteredThrough());
            System.out.println(user.getCompetences());
        }
    }

}




















