package com.sheva;

import com.sheva.domain.Gym;
import com.sheva.domain.User;
import com.sheva.service.GymService;
import com.sheva.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sheva");

        UserService userService = context.getBean(UserService.class);


        List<User> users = userService.findAll();

        for (User user : users) {
            System.out.println(user);
        }


        User user = new User();
        user.setUserName("Anton");
        user.setUserSurname("Kireenko");
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCountry("Ukraine");
        user.setCity("Lviv");
        user.setIsDeleted(false);
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setUserLatitude(684684);
        user.setUserLongitude(8432313);
        user.setMobileNumber(68432224L);

        System.out.println(user);

        User user1 = userService.create(user);
        System.out.println(user1);

        User user2 = userService.update(user,7L);

        System.out.println(user2);

        User users1 = userService.getUserByFullName("Siarhei", "Shautsou");
        System.out.println(users1);

        GymService service = context.getBean(GymService.class);
        List<Gym> gyms = service.findAll();
        for (Gym gym : gyms) {
            System.out.println(gym);
        }

        Gym gym = new Gym();

        gym.setGymName("SportLife");
        gym.setCountry("Belarus");
        gym.setCity("Minsk");
        gym.setSquare(500);
        gym.setCreationDate(new Timestamp(new Date().getTime()));
        gym.setModificationDate(new Timestamp(new Date().getTime()));
        gym.setGymLatitude(4313313);
        gym.setGymLongitude(6846846);
        gym.setIsDeleted(false);

//        Gym gym1 = service.create(gym);
        Gym gym1 = service.update(gym,3);
        System.out.println(gym1);

        Map<String,Object> userStats = userService.getUserStats();

        for (Map.Entry<String, Object> entry : userStats.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }




    }

}
