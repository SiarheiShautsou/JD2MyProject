package com.sheva.controller.converters.requests.trainings;

import com.sheva.controller.requests.trainings.TrainingCreateRequest;
import com.sheva.domain.Training;
import com.sheva.domain.TrainingType;
import com.sheva.repository.TrainingTypeSpringDataRepository;
import com.sheva.service.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


public abstract class TrainingBaseConverter<S, T> implements Converter<S, T> {

    @Autowired
    @Qualifier("userService")
    private final UserServiceInterface userService;

    @Autowired
    @Qualifier("trainingTypeSpringDataRepository")
    private final TrainingTypeSpringDataRepository trainingTypeRepository;

    protected TrainingBaseConverter(UserServiceInterface userService, TrainingTypeSpringDataRepository trainingTypeRepository) {
        this.userService = userService;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    protected Training doConvert(Training training, TrainingCreateRequest source) {

        training.setClient(userService.findClientByClientNameAndSurname(source.getClientName(), source.getClientSurname()));
        training.setTrainer(userService.findTrainerByNameAndSurname(source.getTrainerName(), source.getTrainerSurname()));
        training.setTrainingDate(source.getTrainingDate());
        training.setModificationDate(new Timestamp(new Date().getTime()));

        Optional<TrainingType> result = trainingTypeRepository.findByTrainingTypeName(source.getTrainingTypeName());
        TrainingType trainingType = new TrainingType();

        if(result.isPresent()) {
            trainingType = result.get();
        }else {
            throw new EntityNotFoundException(String.format("Training type %s isn't found", source.getTrainingTypeName()));
        }

        training.setTrainingType(trainingType);

        return training;
    }
}
