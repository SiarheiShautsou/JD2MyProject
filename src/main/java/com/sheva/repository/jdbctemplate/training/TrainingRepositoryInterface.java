package com.sheva.repository.jdbctemplate.training;

import com.sheva.domain.Training;
import com.sheva.domain.User;
import com.sheva.repository.CRUDRepository;

import java.util.List;

public interface TrainingRepositoryInterface extends CRUDRepository<Long, Training> {

    List<Training> findTrainingsByUser(User user);
}
