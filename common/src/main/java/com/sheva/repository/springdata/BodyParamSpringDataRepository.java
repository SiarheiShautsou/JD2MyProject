package com.sheva.repository.springdata;

import com.sheva.domain.BodyParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyParamSpringDataRepository extends JpaRepository<BodyParameters, Long> {
}
