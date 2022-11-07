package com.sheva.service.body_parameters;

import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import com.sheva.exception.NonSuchEntityException;
import com.sheva.repository.BodyParamSpringDataRepository;
import com.sheva.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BodyParamsService implements BodyParamsServiceInterface {

    private final BodyParamSpringDataRepository bodyParamRepository;


    @Override
    public BodyParameters findBodyParametersById(Long id) {

        Optional<BodyParameters> result = bodyParamRepository.findById(id);
        BodyParameters bodyParameters;
        if (result.isPresent()) {
            bodyParameters = result.get();
        } else {
            throw new NonSuchEntityException(
                    (String.format("Body parameters with this id \"%s\" is not found", id)), 404, UUIDGenerator.generateUUID());
        }
        return bodyParameters;
    }

    @Override
    public List<BodyParameters> findAllBodyParameters() {

        return bodyParamRepository.findAll();
    }

    @Override
    public List<BodyParameters> findAllUserBodyParameters(User user) {

        return bodyParamRepository.findAllByUser(user);
    }

    @Override
    public BodyParameters addNewBodyParameters(BodyParameters params) {

        return bodyParamRepository.save(params);
    }

    @Override
    public BodyParameters deleteBodyParameters(BodyParameters params) {

        bodyParamRepository.delete(params);
        return params;
    }
}
