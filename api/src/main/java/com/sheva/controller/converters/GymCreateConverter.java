package com.sheva.controller.converters;

import com.sheva.controller.requests.GymCreateRequest;
import com.sheva.domain.Gym;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Integer.parseInt;

@Component
public class GymCreateConverter extends GymBaseConverter<GymCreateRequest, Gym> {

    @Override
    public Gym convert(GymCreateRequest source) {

        Gym gym = new Gym();
        gym.setCreationDate(new Timestamp(new Date().getTime()));
        gym.setGymLatitude(8468464);
        gym.setGymLongitude(46846468);

        return doConvert(gym, source);
    }
}
