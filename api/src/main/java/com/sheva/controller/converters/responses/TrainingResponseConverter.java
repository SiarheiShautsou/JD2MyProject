package com.sheva.controller.converters.responses;

import com.sheva.controller.responses.TrainingResponse;
import com.sheva.domain.Training;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TrainingResponseConverter implements Converter<Training, TrainingResponse> {
    @Override
    public TrainingResponse convert(Training source) {

        TrainingResponse response= new TrainingResponse();
        response.setTrainerName(source.getTrainer().getUserName());
        response.setTrainerSurname(source.getTrainer().getUserSurname());
        response.setTrainingDate(source.getTrainingDate());
        response.setTrainingTypeName(source.getTrainingType().getTrainingTypeName());

        return response;
    }
}
