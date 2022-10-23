package com.sheva.controller.converters.requests;

import com.sheva.controller.converters.requests.GymBaseConverter;
import com.sheva.controller.requests.GymChangeRequest;
import com.sheva.domain.Gym;
import com.sheva.repository.springdata.GymSpringDataRepository;
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
