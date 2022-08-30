package com.sheva.service;

import com.sheva.domain.Gym;

import java.util.List;
import java.util.Map;

public interface GymService {

    List<Gym> findAll();

    Map<String, Object> getGymStats();

    Gym create(Gym object);

    Gym update(Gym object, Integer id);
}
