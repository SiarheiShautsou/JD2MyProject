package com.sheva.controller.converters.requests.trainings;

import com.sheva.controller.requests.trainings.TrainingChangeRequest;
import com.sheva.domain.Training;
import com.sheva.repository.TrainingTypeSpringDataRepository;
import com.sheva.service.training.TrainingServiceInterface;
import com.sheva.service.user.UserServiceInterface;
import org.springframework.stereotype.Component;

@Component
public class TrainingChangeConverter extends TrainingBaseConverter<TrainingChangeRequest, Training> {

    private final TrainingServiceInterface trainingService;

    protected TrainingChangeConverter(UserServiceInterface userService, TrainingTypeSpringDataRepository trainingTypeRepository, TrainingServiceInterface trainingService) {
        super(userService, trainingTypeRepository);
        this.trainingService = trainingService;
    }


    @Override
    public Training convert(TrainingChangeRequest source) {

        Training training  = trainingService.findTrainingById(source.getTrainingId());

        return doConvert(training, source);
    }
}
