package com.sheva.controller;

import com.sheva.controller.requests.subscription.SubscriptionChangeRequest;
import com.sheva.controller.requests.subscription.SubscriptionCreateRequest;
import com.sheva.controller.responses.SubscriptionResponse;
import com.sheva.domain.Gym;
import com.sheva.domain.Subscription;
import com.sheva.domain.User;
import com.sheva.service.gym.GymServiceInterface;
import com.sheva.service.subscription.SubscriptionServiceInterface;
import com.sheva.service.user.UserServiceInterface;
import com.sheva.util.MapGenerator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/subscription")
public class SubscriptionController {

    private final SubscriptionServiceInterface subscriptionService;

    private final UserServiceInterface userService;

    private final GymServiceInterface gymService;

    private final ConversionService converter;


    @Operation(summary = "Create a new subscription")
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Object> createSubscription(@RequestBody SubscriptionCreateRequest request) {

        Subscription subscription = convertFromCreateRequest(request);

        Subscription createdSubscription = subscriptionService.createSubscription(subscription);

        SubscriptionResponse response = converter.convert(createdSubscription, SubscriptionResponse.class);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(response), HttpStatus.OK);

    }

    @Operation(summary = "Update a subscription")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Object> updateSubscription(@RequestBody SubscriptionChangeRequest request) {

        Subscription subscription = convertFromChangeRequest(request);

        Subscription updatedSubscription = subscriptionService.createSubscription(subscription);

        SubscriptionResponse response = converter.convert(updatedSubscription, SubscriptionResponse.class);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(response), HttpStatus.OK);

    }

    @Operation(summary = "Delete a subscription")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteSubscription(@PathVariable String id) {

        Long subscriptionId = Long.parseLong(id);

        Subscription deletedSubscription = subscriptionService.deleteSubscription(subscriptionId);

        SubscriptionResponse response = converter.convert(deletedSubscription, SubscriptionResponse.class);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(response), HttpStatus.OK);
    }

    private Subscription convertFromCreateRequest(SubscriptionCreateRequest request) {

        Subscription subscription = new Subscription();

        subscription.setValidFrom(request.getValidFrom());
        subscription.setValidTo(request.getValidTo());
        subscription.setIsUnlimited(request.getIsUnlimited());
        subscription.setTrainingsCount(request.getTrainingsCount());

        User user = userService.findClientByClientNameAndSurname(request.getUserName(), request.getUserSurname());
        Gym gym = gymService.findGymByName(request.getGymName());

        subscription.setUser(user);
        subscription.setGym(gym);
        return subscription;
    }

    private Subscription convertFromChangeRequest(SubscriptionChangeRequest request) {

        Subscription subscription = convertFromCreateRequest(request);
        subscription.setId(request.getId());

        return subscription;
    }


}
