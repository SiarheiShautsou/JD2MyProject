package com.sheva.controller;

import com.sheva.controller.requests.gym.GymChangeRequest;
import com.sheva.controller.requests.gym.GymCreateRequest;
import com.sheva.controller.responses.GymResponse;
import com.sheva.domain.Gym;
import com.sheva.service.gym.GymServiceInterface;
import com.sheva.util.MapGenerator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Integer.parseInt;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/gyms")
public class GymController {

    private final GymServiceInterface gymService;

    private final ConversionService converter;

    @Operation(summary = "Search gym by name")
    @GetMapping("/{name}")
    public ResponseEntity<Object> findGymByGymName(@PathVariable String name) {

        Gym gym = gymService.findGymByName(name);
        GymResponse response = converter.convert(gym, GymResponse.class);
        return new ResponseEntity<>(MapGenerator.convertObjectToMap(response), HttpStatus.OK);
    }

    @Operation(summary = "Find gym by gym's Id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findGymById(@PathVariable String id) {

        Integer gymId = parseInt(id);
        Gym gym = gymService.findGymById(gymId);
        return new ResponseEntity<>(MapGenerator.convertObjectToMap(gym), HttpStatus.OK);
    }

    @Operation(summary = "Create a new gym")
    @PostMapping()
    @Transactional
    public ResponseEntity<Object> createGym(@RequestBody GymCreateRequest request) {

        Gym gym = converter.convert(request, Gym.class);
        Gym createdGym = gymService.createNewGym(gym);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(createdGym), HttpStatus.CREATED);

    }

    @Operation(summary = "Update gym's information")
    @PutMapping
    @Transactional
    public ResponseEntity<Object> updateGym(@RequestBody GymChangeRequest changeRequest) {

        Gym gym = converter.convert(changeRequest, Gym.class);
        Gym updatedGym = gymService.createNewGym(gym);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(updatedGym), HttpStatus.OK);

    }

    @Operation(summary = "Delete a gym")
    @PatchMapping("/is-deleted/{id}")
    @Transactional
    public ResponseEntity<Object> getGymIsDeleted(@PathVariable String id) {

        Integer gymId = Integer.parseInt(id);
        Gym gym = gymService.findGymById(gymId);
        gym.setIsDeleted(true);
        Gym isDeletedGym = gymService.createNewGym(gym);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(isDeletedGym), HttpStatus.OK);

    }

    @Operation(summary = "Delete a gym forever")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteGym(@PathVariable String id) {

        Integer gymId = Integer.parseInt(id);
        Gym gym = gymService.findGymById(gymId);
        gymService.deleteGym(gym);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(gym), HttpStatus.OK);
    }
}
