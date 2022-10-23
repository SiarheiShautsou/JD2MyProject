package com.sheva.controller.springdata;

import com.sheva.repository.springdata.TrainingSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data/trainings")
public class TrainingController {

    private final TrainingSpringDataRepository trainingRepository;

    @Operation(summary = "Find all trainings")
    @GetMapping()
    public ResponseEntity<Object> findAllTrainings(){
        return new ResponseEntity<>(Collections.singletonMap("result", trainingRepository.findAll()), HttpStatus.OK);
    }


}
