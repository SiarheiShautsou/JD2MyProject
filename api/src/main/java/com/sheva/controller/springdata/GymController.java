package com.sheva.controller.springdata;

import com.sheva.controller.requests.gym.GymChangeRequest;
import com.sheva.controller.requests.gym.GymCreateRequest;
import com.sheva.domain.Gym;
import com.sheva.repository.GymSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/gyms")
public class GymController {

    private final GymSpringDataRepository gymRepository;

    private final ConversionService converter;


    @Operation(summary = "Find all gyms")
    @GetMapping()
    public ResponseEntity<Object> findAllGyms() {
        return new ResponseEntity<>(Collections.singletonMap("result", gymRepository.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Find gym by gym's Id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findGymById(@PathVariable String id) {

        Integer gymId = parseInt(id);
        return new ResponseEntity<>(Collections.singletonMap("result", gymRepository.findById(gymId)), HttpStatus.OK);
    }

    @Operation(summary = "Create a new gym")
    @PostMapping()
    public ResponseEntity<Object> createGym(@RequestBody GymCreateRequest request) {

        Gym gym = converter.convert(request, Gym.class);
        Gym createdGym = gymRepository.save(gym);

        Map<String, Object> model = new HashMap<>();
        model.put("gym", createdGym);

        return new ResponseEntity<>(model, HttpStatus.CREATED);

    }

    @Operation(summary = "Update gym's information")
    @PutMapping
    public ResponseEntity<Object> updateGym(@RequestBody GymChangeRequest changeRequest){

        Gym gym = converter.convert(changeRequest, Gym.class);
        Gym updatedGym = gymRepository.save(gym);

        Map<String, Object> model = new HashMap<>();
        model.put("gym", updatedGym);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @Operation(summary = "Delete a gym")
    @PatchMapping("/is-deleted/{id}")
    public ResponseEntity<Object> getGymIsDeleted(@PathVariable String id){

        Integer gymId = Integer.parseInt(id);
        Gym gym = gymRepository.findById(gymId).orElse(null);
        assert gym != null;
        gym.setIsDeleted(true);
        gymRepository.flush();

        Map<String, Object> model = new HashMap<>();
        model.put("gym is deleted", gym);
        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @Operation(summary = "Delete a gym forever")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteGym(@PathVariable String id) {

        Integer gymId = Integer.parseInt(id);
        Gym gym = gymRepository.findById(gymId).orElse(null);
        gymRepository.delete(gym);
        Map<String, Object> model = new HashMap<>();
        model.put("deleted gym", gym);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
