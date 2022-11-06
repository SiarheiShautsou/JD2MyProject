package com.sheva.service.subscription;

import com.sheva.domain.Gym;
import com.sheva.domain.Subscription;
import com.sheva.domain.User;

import java.util.List;

public interface SubscriptionServiceInterface {

    Subscription findSubscriptionById(Long id);

    Subscription createSubscription(Subscription subscription);

    Subscription deleteSubscription(Long id);

    List<Subscription> findAllUserSubscriptions(User user);

    Subscription findUserValidSubscription(User user, Gym gym);
}
