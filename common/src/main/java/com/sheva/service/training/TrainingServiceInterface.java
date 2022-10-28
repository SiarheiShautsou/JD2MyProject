package com.sheva.service.training;

import com.sheva.domain.Training;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TrainingServiceInterface {

    Training findTrainingById(Long id);

    List<Training> findAllClientTrainings(String clientName, String clientSurname);

    Training createTraining(Training training);

    Training updateTraining(Training training);

    Page<Training> findAllTrainings();

    Training deleteTraining(Long id);
}
