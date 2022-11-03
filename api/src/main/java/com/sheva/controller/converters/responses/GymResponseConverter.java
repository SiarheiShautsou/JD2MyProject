package com.sheva.controller.converters.responses;

import com.sheva.controller.responses.GymResponse;
import com.sheva.domain.Gym;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GymResponseConverter implements Converter<Gym, GymResponse> {

    @Override
    public GymResponse convert(Gym source) {

        GymResponse response = new GymResponse();

        response.setGymName(source.getGymName());
        response.setCountry(source.getCountry());
        response.setCity(source.getCity());
        response.setStreet(source.getGymStreet());
        response.setBuilding(source.getGymBuilding());
        response.setSquare(source.getSquare());

        return response;
    }
}
