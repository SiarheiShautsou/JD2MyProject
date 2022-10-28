package com.sheva.repository;

import com.sheva.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingSpringDataRepository extends JpaRepository<Training, Long> {

    Optional<Training> findTrainingById(Long id);
    List<Training> findAllByClientUserNameAndClientUserSurname(String userName, String userSurname);
}
