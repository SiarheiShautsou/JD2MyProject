package com.sheva.controller.converters;

import com.sheva.controller.requests.GymChangeRequest;
import com.sheva.controller.requests.GymCreateRequest;
import com.sheva.domain.Gym;
import com.sheva.repository.springdata.GymSpringDataRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class GymChangeConverter extends GymBaseConverter <GymChangeRequest, Gym> {

    private final GymSpringDataRepository gymRepository;

    @Override
    public Gym convert(GymChangeRequest source) {

        Gym gym = gymRepository.findById(source.getId()).get();

        return doConvert(gym, source);
    }
}
