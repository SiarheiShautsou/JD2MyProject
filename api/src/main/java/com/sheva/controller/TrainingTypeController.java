package com.sheva.controller;

import com.sheva.domain.TrainingType;
import com.sheva.repository.TrainingTypeSpringDataRepository;
import com.sheva.util.MapGenerator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/training_types")
public class TrainingTypeController {

    private final TrainingTypeSpringDataRepository trainingTypeRepository;

    @Operation(summary = "Search all training types")
    @GetMapping()
    public ResponseEntity<Object> findAllTrainingTypes() {

        return new ResponseEntity<>(Collections.singletonMap("result", trainingTypeRepository.findAllTrainingTypesInCache()),
                HttpStatus.OK);
    }

    @Operation(summary = "Search training type by id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findTrainingTypeById(@PathVariable String id) {

        Integer trainingTypeID = Integer.parseInt(id);

        return new ResponseEntity<>(Collections.singletonMap("result", trainingTypeRepository.findById(trainingTypeID)),
                HttpStatus.OK);
    }

    @Operation(summary = "Search training type by name")
    @Transactional
    @PostMapping("/create/{trainingName}")
    public ResponseEntity<Object> createTrainingType(@PathVariable String trainingName) {

        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName(trainingName);

        TrainingType createdTrainingType = trainingTypeRepository.save(trainingType);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(createdTrainingType), HttpStatus.CREATED);

    }


}

