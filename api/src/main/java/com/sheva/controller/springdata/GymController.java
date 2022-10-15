package com.sheva.controller.springdata;

import com.sheva.controller.requests.GymCreateRequest;
import com.sheva.domain.Gym;
import com.sheva.repository.springdata.GymSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/gyms")
public class GymController {

    private final GymSpringDataRepository gymRepository;

    @GetMapping()
    public ResponseEntity<Object> findAllGyms() {
        return new ResponseEntity<>(Collections.singletonMap("result", gymRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findGymById(@PathVariable String id) {

        Integer gymId = parseInt(id);
        return new ResponseEntity<>(Collections.singletonMap("result", gymRepository.findById(gymId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createGym(@RequestBody GymCreateRequest request) {

        Gym gym = new Gym();
        gym.setGymName(request.getGymName());
        gym.setCountry(request.getCountry());
        gym.setCity(request.getCity());
        gym.setSquare(parseInt(request.getSquare()));
        gym.setCreationDate(new Timestamp(new Date().getTime()));
        gym.setModificationDate(new Timestamp(new Date().getTime()));
        gym.setGymLatitude(8468464);
        gym.setGymLongitude(46846468);
        gym.setIsDeleted(false);

        Gym createdGym = gymRepository.save(gym);

        Map<String, Object> model = new HashMap<>();
        model.put("gym", createdGym);

        return new ResponseEntity<>(model, HttpStatus.CREATED);

    }
}
