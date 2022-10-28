package com.sheva.controller.converters.requests.trainings;

import com.sheva.controller.requests.trainings.TrainingCreateRequest;
import com.sheva.domain.Training;
import com.sheva.repository.TrainingTypeSpringDataRepository;
import com.sheva.service.user.UserService;
import com.sheva.service.user.UserServiceInterface;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class TrainingCreateConverter extends TrainingBaseConverter<TrainingCreateRequest, Training> {


    protected TrainingCreateConverter(UserServiceInterface userService, TrainingTypeSpringDataRepository trainingTypeRepository) {
        super(userService, trainingTypeRepository);
    }

    @Override
    public Training convert(TrainingCreateRequest source) {

        Training training = new Training();

        training.setCreationDate(new Timestamp(new Date().getTime()));

        return doConvert(training, source);
    }
}
