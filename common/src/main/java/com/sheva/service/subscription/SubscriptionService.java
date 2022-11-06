package com.sheva.service.subscription;

import com.sheva.domain.Gym;
import com.sheva.domain.Subscription;
import com.sheva.domain.User;
import com.sheva.repository.SubscriptionSpringDataRepository;
import com.sheva.service.user.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements SubscriptionServiceInterface{

    private final SubscriptionSpringDataRepository subscriptionRepository;

    private final UserServiceInterface userService;

    @Override
    public Subscription findSubscriptionById(Long id) {

        Optional<Subscription> result = subscriptionRepository.findById(id);
        Subscription subscription;

        if (result.isPresent()) {
            subscription = result.get();
        }else {
            throw new EntityNotFoundException(String.format("Subscription with id %s not found", id));
        }

        return subscription;
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription deleteSubscription(Long id) {

        Subscription subscription = findSubscriptionById(id);
        subscriptionRepository.deleteById(id);
        return subscription;
    }

    @Override
    public List<Subscription> findAllUserSubscriptions(User user) {

        return subscriptionRepository.findAllByUser(user);
    }

    @Override
    public Subscription findUserValidSubscription(User user, Gym gym) {

        List<Subscription> allUserSubscriptions = findAllUserSubscriptions(user);
        Subscription validSubscription = null;
        for (Subscription subscription : allUserSubscriptions) {
            if(subscription.getGym().equals(gym) && Boolean.TRUE.equals(checkSubscriptionTimeValid(subscription))) {
                if (Boolean.TRUE.equals(subscription.getIsUnlimited()) || subscription.getTrainingsCount() > 0) {
                    validSubscription = subscription;
                }else {
                    throw new EntityNotFoundException(String.format("Training count of subscription = %d", subscription.getTrainingsCount()));
                }
            }
        }
        if(validSubscription == null){
            throw new EntityNotFoundException(String.format("Valid subscriptions for %s are not found.", gym.getGymName()));
        }
        return validSubscription;
    }

    private Boolean checkSubscriptionTimeValid(Subscription subscription) {

        Timestamp validFrom = subscription.getValidFrom();
        Timestamp validTo = subscription.getValidTo();
        Timestamp currentTime = new Timestamp(new Date().getTime());
        return (validFrom.before(currentTime) && validTo.after(currentTime));

    }

}
