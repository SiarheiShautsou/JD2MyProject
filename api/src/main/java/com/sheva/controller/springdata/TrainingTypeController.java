package com.sheva.controller.springdata;

import com.sheva.domain.TrainingType;
import com.sheva.repository.TrainingTypeSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/types")
public class TrainingTypeController {

    private final TrainingTypeSpringDataRepository trainingTypeRepository;

    @GetMapping()
    public ResponseEntity<Object> findAllTrainingTypes(){

        return new ResponseEntity<>(Collections.singletonMap("result", trainingTypeRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create/{trainingName}")
    public ResponseEntity<Object> createTrainingType(@PathVariable String trainingName){

        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName(trainingName);

        TrainingType createdTrainingType = trainingTypeRepository.save(trainingType);

        Map<String, Object> model = new HashMap<>();
        model.put("trainingType", createdTrainingType);

        return new ResponseEntity<>(model, HttpStatus.CREATED);

    }


}

