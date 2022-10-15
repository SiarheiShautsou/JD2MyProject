package com.sheva.service;

import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import com.sheva.repository.jdbctemplate.bodyparam.JdbcTemplBodyParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class BodyParamServiceImp implements BodyParamService{

    private final JdbcTemplBodyParamRepository bodyParamRepository;
    @Override
    public BodyParameters findById(Long id) {
        return bodyParamRepository.findById(id);
    }

    @Override
    public List<BodyParameters> findBodyParamByUser(User user) {
        return bodyParamRepository.findBodyParamByUser(user);
    }

    @Override
    public BodyParameters createBodyParamForUser(BodyParameters parameters, User user) {
        return bodyParamRepository.createBodyParamForUser(parameters, user);
    }
}
