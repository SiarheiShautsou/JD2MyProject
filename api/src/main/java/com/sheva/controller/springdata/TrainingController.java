package com.sheva.controller.springdata;

import com.sheva.controller.requests.trainings.TrainingChangeRequest;
import com.sheva.controller.requests.trainings.TrainingCreateRequest;
import com.sheva.domain.Training;
import com.sheva.repository.TrainingSpringDataRepository;
import com.sheva.service.training.TrainingServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data/trainings")
public class TrainingController {

    private final TrainingSpringDataRepository trainingRepository;

    private final TrainingServiceInterface trainingService;

    private final ConversionService converter;

    @Operation(summary = "Find all trainings")
    @GetMapping()
    public ResponseEntity<Object> findAllTrainings(){
        return new ResponseEntity<>(Collections.singletonMap("result", trainingService.findAllTrainings()), HttpStatus.OK);
    }

    @Operation(summary = "Find all client's trainings")
    @GetMapping("/trainings-client/{name}&{surname}")

    public ResponseEntity<Object> findAllClientTrainings(@PathVariable("name") String name, @PathVariable("surname") String surname){

        return new ResponseEntity<>(Collections.singletonMap("result",
                trainingService.findAllClientTrainings(name, surname)), HttpStatus.OK);
    }

    @Operation(summary = "Create a new training")
    @PostMapping("/create-training")
    public ResponseEntity<Object> createTraining(@RequestBody TrainingCreateRequest request){

        Training training = converter.convert(request, Training.class);

        Training createdTraining = trainingService.createTraining(training);

        return new ResponseEntity<>(Collections.singletonMap("created", createdTraining), HttpStatus.CREATED);
    }

    @PutMapping("/update-training")
    public ResponseEntity<Object> updateTraining(@RequestBody TrainingChangeRequest request){

        Training training = converter.convert(request, Training.class);

        Training updatedTraining = trainingService.updateTraining(training);

        return new ResponseEntity<>(Collections.singletonMap("updated", updatedTraining), HttpStatus.OK);
    }

    @Operation(summary = "Delete a training")
    @DeleteMapping("/delete-training/{id}")
    public ResponseEntity<Object> deleteTraining(@PathVariable String id){

        Long trainingId = Long.parseLong(id);
        Training deletedTraining = trainingService.deleteTraining(trainingId);
        return new ResponseEntity<>(Collections.singletonMap("deleted", deletedTraining), HttpStatus.OK);
    }
}
