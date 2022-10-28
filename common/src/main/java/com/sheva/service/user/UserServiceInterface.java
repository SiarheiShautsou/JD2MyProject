package com.sheva.service.user;


import com.sheva.domain.User;

import java.util.List;

public interface UserServiceInterface {

    public User findUserById(Long id);

    List<User> findAllTrainersInCity(String city);

    User createClientAccount(User user);

    User createTrainerAccount(User user, String gymName);

    User updateUser(User user);

    User findTrainerByNameAndSurname(String name, String surname);

        User findClientByClientNameAndSurname(String name, String surname);
}
