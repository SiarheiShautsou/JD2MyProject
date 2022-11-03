package com.sheva.controller.converters.requests;

import com.sheva.controller.requests.BodyParamCreateRequest;
import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import com.sheva.service.user.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BodyParamsConverter implements Converter<BodyParamCreateRequest, BodyParameters> {

    private final UserServiceInterface userService;

    @Override
    public BodyParameters convert(BodyParamCreateRequest source) {

        User user = userService.findUserByNameAndSurname(source.getUserName(), source.getUserSurname());

        BodyParameters bodyParameter = new BodyParameters();
        bodyParameter.setUser(user);
        bodyParameter.setHeight(source.getHeight());
        bodyParameter.setWeight(source.getWeight());
        bodyParameter.setBust(source.getBust());
        bodyParameter.setWaist(source.getWaist());
        bodyParameter.setHip(source.getHip());
        bodyParameter.setCreationDate(source.getCreationDate());

        return bodyParameter;
    }
}
