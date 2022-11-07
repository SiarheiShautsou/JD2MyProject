package com.sheva.service.gym;

import com.sheva.domain.Gym;
import com.sheva.exception.NonSuchEntityException;
import com.sheva.repository.GymSpringDataRepository;
import com.sheva.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymService implements GymServiceInterface {

    private final GymSpringDataRepository gymRepository;

    @Override
    public Gym findGymById(Integer id) {
        Optional<Gym> result = gymRepository.findById(id);
        Gym gym;
        if (result.isPresent()) {
            gym = result.get();
        } else {
            throw new NonSuchEntityException(
                    (String.format("Gym with this id %d not found", id)), 404, UUIDGenerator.generateUUID());
        }
        return gym;
    }

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
        } else {
            throw new NonSuchEntityException(String.format(
                    String.format("Gym \"%s\" is not found", name)), 404, UUIDGenerator.generateUUID());
        }
        return gym;
    }

    @Override
    public Gym createNewGym(Gym gym) {

        return gymRepository.save(gym);
    }

    @Override
    public Gym deleteGym(Gym gym) {

        gymRepository.delete(gym);
        return gym;
    }
}
