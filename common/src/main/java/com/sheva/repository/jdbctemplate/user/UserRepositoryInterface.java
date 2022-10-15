package com.sheva.repository.jdbctemplate.user;

import com.sheva.domain.User;
import com.sheva.repository.CRUDRepository;

import java.util.List;
import java.util.Map;

public interface UserRepositoryInterface extends CRUDRepository<Long, User> {

    Map<String, Object> getUserStats();

    User getUserByFullName(String name, String surname);

    List<User> findUserByTrainingType(String trainingType);

}
