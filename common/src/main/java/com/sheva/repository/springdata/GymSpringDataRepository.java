package com.sheva.repository.springdata;

import com.sheva.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymSpringDataRepository extends JpaRepository<Gym, Integer> {
}
