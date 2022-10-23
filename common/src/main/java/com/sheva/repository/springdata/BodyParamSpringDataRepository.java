package com.sheva.repository.springdata;

import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyParamSpringDataRepository extends JpaRepository<BodyParameters, Long> {

    List<BodyParameters> findAllByUser(User user);
}
