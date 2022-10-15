package com.sheva.service;

import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;

import java.util.List;

public interface BodyParamService {

    BodyParameters findById(Long id);

    List<BodyParameters> findBodyParamByUser(User user);

    BodyParameters createBodyParamForUser(BodyParameters parameters, User user);
}
