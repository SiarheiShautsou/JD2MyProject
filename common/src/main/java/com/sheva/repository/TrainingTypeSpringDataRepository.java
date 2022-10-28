package com.sheva.repository;

import com.sheva.domain.TrainingType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingTypeSpringDataRepository extends JpaRepository<TrainingType, Integer> {

    @Cacheable("training types")
    @Query(value = "select t from TrainingType t")
    List<TrainingType> findAllCustomQuery();

    Optional<TrainingType> findByTrainingTypeName(String trainingTypeName);
}
