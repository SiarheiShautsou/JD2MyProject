package com.sheva.service.gym;

import com.sheva.domain.Gym;
import com.sheva.repository.GymSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymService implements GymServiceInterface{

    private final GymSpringDataRepository gymRepository;

    @Override
    public Page<Gym> findAllGyms(Pageable pageable) {
        return gymRepository.findAll(pageable);
    }

    @Override
    public Gym findGymByName(String name) {

        Optional<Gym> result = gymRepository.findGymByGymName(name);
        Gym gym;
        if (result.isPresent()) {
            gym = result.get();
        }else {
            throw new EntityNotFoundException(String.format(
                    String.format("Gym \"%s\" is not found", name)));
        }
         return gym;
    }
}
