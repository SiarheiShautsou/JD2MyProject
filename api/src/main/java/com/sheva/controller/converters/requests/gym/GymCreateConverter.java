package com.sheva.controller.converters.requests.gym;

import com.sheva.controller.requests.gym.GymCreateRequest;
import com.sheva.domain.Gym;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class GymCreateConverter extends GymBaseConverter<GymCreateRequest, Gym> {

    @Override
    public Gym convert(GymCreateRequest source) {

        Gym gym = new Gym();
        gym.setCreationDate(new Timestamp(new Date().getTime()));
        gym.setGymStreet(source.getStreet());
        gym.setGymBuilding(source.getBuilding());

        return doConvert(gym, source);
    }
}
