package com.sheva.service.gym;

import com.sheva.domain.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GymServiceInterface {

    Gym findGymById(Integer id);
    Page<Gym> findAllGyms(Pageable pageable);

    Gym findGymByName(String name);

    Gym createNewGym(Gym gym);

    Gym deleteGym(Gym gym);
}
