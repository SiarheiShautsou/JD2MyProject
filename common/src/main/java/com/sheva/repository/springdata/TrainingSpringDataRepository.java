package com.sheva.repository.springdata;

import com.sheva.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingSpringDataRepository extends JpaRepository<Training, Long> {
}
