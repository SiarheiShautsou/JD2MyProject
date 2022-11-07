package com.sheva.controller.converters.requests.gym;

import com.sheva.controller.requests.gym.GymChangeRequest;
import com.sheva.domain.Gym;
import com.sheva.service.gym.GymServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class GymChangeConverter extends GymBaseConverter<GymChangeRequest, Gym> {

    private final GymServiceInterface gymService;

    @Override
    public Gym convert(GymChangeRequest source) {

        Gym gym = gymService.findGymById(source.getId());

        return doConvert(gym, source);
    }
}
