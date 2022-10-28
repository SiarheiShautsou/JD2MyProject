package com.sheva.service.training;

import com.sheva.domain.Training;
import com.sheva.repository.TrainingSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.swing.plaf.SpinnerUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingService implements TrainingServiceInterface{

    private final TrainingSpringDataRepository trainingRepository;

    @Override
    public Training findTrainingById(Long id) {

        Optional<Training> result = trainingRepository.findTrainingById(id);
        Training training = new Training();
        if (result.isPresent()) {
            training = result.get();
        }else {
            throw new EntityNotFoundException(String.format("Training with id %d not found", id));
        }

        return training;
    }

    @Override
    public Page<Training> findAllTrainings(){

        return trainingRepository.findAll(PageRequest.of(0, 10));
    }

    @Override
    public List<Training> findAllClientTrainings(String clientName, String clientSurname){

        return trainingRepository.findAllByClientUserNameAndClientUserSurname(clientName, clientSurname);
    }

    @Override
    @Transactional
    public Training createTraining(Training training) {

        return trainingRepository.save(training);
    }

    @Override
    @Transactional
    public Training updateTraining(Training training) {

        trainingRepository.flush();
        return training;
    }

    @Override
    @Transactional
    public Training deleteTraining(Long id) {

        Optional<Training> result = trainingRepository.findById(id);
        Training training = new Training();
        if (result.isPresent()) {
            training = result.get();
        }
        trainingRepository.delete(training);

        return training;

    }
}
