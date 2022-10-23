package com.sheva.repository.springdata;

import com.sheva.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymSpringDataRepository extends JpaRepository<Gym, Integer> {

    Optional<Gym> findGymByGymName(String name);

}
