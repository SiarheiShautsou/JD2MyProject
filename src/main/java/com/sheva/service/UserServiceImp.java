package com.sheva.service;

import com.sheva.domain.User;
import com.sheva.repository.jdbctemplate.user.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImp implements UserService {

    private final UserRepositoryInterface userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Map<String, Object> getUserStats() {
        return userRepository.getUserStats();
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public User update(User object, Long id) {
        return userRepository.update(object, id);
    }

    @Override
    public User getUserByFullName(String name, String surname) {
        return userRepository.getUserByFullName(name, surname);
    }
}
