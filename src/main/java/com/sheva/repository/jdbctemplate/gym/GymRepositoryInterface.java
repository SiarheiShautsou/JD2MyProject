package com.sheva.repository.jdbctemplate.gym;

import com.sheva.domain.Gym;
import com.sheva.repository.CRUDRepository;

import java.util.List;
import java.util.Map;

public interface GymRepositoryInterface extends CRUDRepository<Integer, Gym> {

    Map<String, Object> getGymStats();

    List<Gym> findGymByCity(String city);

}
