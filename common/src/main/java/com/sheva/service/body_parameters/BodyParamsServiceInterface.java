package com.sheva.service.body_parameters;

import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;

import java.util.List;

public interface BodyParamsServiceInterface {


    BodyParameters findBodyParametersById(Long id);

    List<BodyParameters> findAllBodyParameters();

    List<BodyParameters> findAllUserBodyParameters(User user);

    BodyParameters addNewBodyParameters(BodyParameters params);

    BodyParameters deleteBodyParameters(BodyParameters params);

}
