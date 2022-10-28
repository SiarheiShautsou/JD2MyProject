package com.sheva.controller.springdata;

import com.sheva.controller.requests.subscription.SubscriptionChangeRequest;
import com.sheva.controller.requests.subscription.SubscriptionCreateRequest;
import com.sheva.domain.Gym;
import com.sheva.domain.Subscription;
import com.sheva.domain.User;
import com.sheva.repository.GymSpringDataRepository;
import com.sheva.repository.SubscriptionSpringDataRepository;
import com.sheva.repository.UserSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/subscription")
public class SubscriptionController {

    private final SubscriptionSpringDataRepository subscriptionRepository;

    private final UserSpringDataRepository userRepository;

    private final GymSpringDataRepository gymRepository;

    @Operation(summary = "Find all subscriptions")
    @GetMapping("/all")
    public ResponseEntity<Object> findAllSubscriptions(){

        return new ResponseEntity<>(Collections.singletonMap("result", subscriptionRepository.findAll()), HttpStatus.OK);
    }


    @Operation(summary = "Create a new subscription")
    @PostMapping("/create")
    public ResponseEntity<Object> createSubscription(@RequestBody SubscriptionCreateRequest request){

        Subscription subscription = new Subscription();

        subscription.setValidFrom(request.getValidFrom());
        subscription.setValidTo(request.getValidTo());
        subscription.setIsUnlimited(request.getIsUnlimited());
        subscription.setTrainingsCount(request.getTrainingsCount());

        User user = userRepository.findUserByUserNameAndUserSurname(request.getUserName(), request.getUserSurname()).get();
        Gym gym = gymRepository.findGymByGymName(request.getGymName()).get();

        subscription.setUser(user);
        subscription.setGym(gym);
        Subscription createdSubscription = subscriptionRepository.save(subscription);

        Map<String, Object> model = new HashMap<>();
        model.put("subscription", createdSubscription);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @Operation(summary = "Update a subscription")
    @PutMapping("/update")
    public ResponseEntity<Object> updateSubscription(@RequestBody SubscriptionChangeRequest request){

        Subscription subscription = subscriptionRepository.findById(request.getId()).get();
        User user = userRepository.findUserByUserNameAndUserSurname(request.getUserName(), request.getUserSurname()).get();
        Gym gym = gymRepository.findGymByGymName(request.getGymName()).get();

        subscription.setUser(user);
        subscription.setGym(gym);
        subscription.setValidFrom(request.getValidFrom());
        subscription.setValidTo(request.getValidTo());
        subscription.setIsUnlimited(request.getIsUnlimited());
        subscription.setTrainingsCount(request.getTrainingsCount());

        subscriptionRepository.flush();

        Map<String, Object> model = new HashMap<>();
        model.put("updated subscription", subscription);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @Operation(summary = "Delete a subscription")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable String id){

        Long subscriptionId = Long.parseLong(id);
        subscriptionRepository.deleteById(subscriptionId);
        return new ResponseEntity<>(Collections.singletonMap("deleted", subscriptionRepository.findById(subscriptionId)), HttpStatus.OK);
    }


}
