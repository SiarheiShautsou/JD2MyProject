package com.sheva.service;

import com.sheva.domain.Gym;
import com.sheva.repository.jdbctemplate.gym.JdbcTemplateGymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Primary
public class GymServiceImp implements GymService{

    private final JdbcTemplateGymRepository jdbcTemplateGymRepository;

    @Override
    public List<Gym> findAll() {
        return jdbcTemplateGymRepository.findAll();
    }

    @Override
    public Map<String, Object> getGymStats() {
        return null;
    }

    @Override
    public Gym create(Gym object) {
        return jdbcTemplateGymRepository.create(object);
    }

    @Override
    public Gym update(Gym object, Integer id) {
        return jdbcTemplateGymRepository.update(object, id);
    }
}
