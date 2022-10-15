package com.sheva.service;

import com.sheva.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    Map<String, Object> getUserStats();

    User create(User object);

    User update(User object, Long id);

    User getUserByFullName(String name, String surname);

    List<User> search(int limit, int offset);

    Long deleteUser(Long id);

}
