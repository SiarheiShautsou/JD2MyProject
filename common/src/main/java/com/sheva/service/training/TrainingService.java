package com.sheva.service.training;

import com.sheva.domain.Subscription;
import com.sheva.domain.Training;
import com.sheva.repository.TrainingSpringDataRepository;
import com.sheva.service.subscription.SubscriptionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingService implements TrainingServiceInterface{

    private final TrainingSpringDataRepository trainingRepository;

    private final SubscriptionServiceInterface subscriptionService;

    @Override
    public Training findTrainingById(Long id) {

        Optional<Training> result = trainingRepository.findTrainingById(id);
        Training training;
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

        Subscription subscription = subscriptionService.findUserValidSubscription(training.getClient(),
                training.getTrainer().getTrainerGym());

        if(subscription.getValidTo().after(training.getTrainingDate())) {
            final int trainingCount = 1;
            if (Boolean.FALSE.equals(subscription.getIsUnlimited())) {
                int userTrainingsCount = subscription.getTrainingsCount();
                int updatedTrainingsCount = userTrainingsCount - trainingCount;
                subscription.setTrainingsCount(updatedTrainingsCount);
                subscriptionService.createSubscription(subscription);
            }
        }else {
            throw new IllegalArgumentException("Training day must be before subscription's expiration date.");
        }
        return trainingRepository.save(training);
    }

    @Override
    @Transactional
    public Training updateTraining(Training training) {

        Training trainingBeforeUpdate = findTrainingById(training.getId());
        Training trainingAfterUpdate;

        if(training.getClient().getUserName().equals(trainingBeforeUpdate.getClient().getUserName()) &&
        training.getClient().getUserSurname().equals(trainingBeforeUpdate.getClient().getUserSurname())){
            trainingAfterUpdate = trainingRepository.save(training);
        }else{
            trainingAfterUpdate = createTraining(training);
        }

        return trainingAfterUpdate;
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
