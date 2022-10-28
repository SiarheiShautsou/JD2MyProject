package com.sheva.controller.converters.requests.gym;

import com.sheva.controller.requests.gym.GymChangeRequest;
import com.sheva.domain.Gym;
import com.sheva.repository.GymSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class GymChangeConverter extends GymBaseConverter<GymChangeRequest, Gym> {

    private final GymSpringDataRepository gymRepository;

    @Override
    public Gym convert(GymChangeRequest source) {

        Gym gym = gymRepository.findById(source.getId()).get();

        return doConvert(gym, source);
    }
}
