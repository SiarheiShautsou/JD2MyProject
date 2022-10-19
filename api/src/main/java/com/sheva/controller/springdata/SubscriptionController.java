package com.sheva.controller.springdata;

import com.sheva.controller.requests.SubscriptionCreateRequest;
import com.sheva.domain.Subscription;
import com.sheva.repository.springdata.GymSpringDataRepository;
import com.sheva.repository.springdata.SubscriptionSpringDataRepository;
import com.sheva.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/all")
    public ResponseEntity<Object> findAllSubscriptions(){

        return new ResponseEntity<>(Collections.singletonMap("result", subscriptionRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createSubscription(@RequestBody SubscriptionCreateRequest request){

        Subscription subscription = new Subscription();

        subscription.setIsUnlimited(request.getIsUnlimited());
        subscription.setTrainingsCount(request.getTrainingsCount());

        Subscription createdSubscription = subscriptionRepository.save(subscription);

        Map<String, Object> model = new HashMap<>();
        model.put("subscription", createdSubscription);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable String id){

        Long subscriptionId = Long.parseLong(id);
        subscriptionRepository.deleteById(subscriptionId);
        return new ResponseEntity<>(Collections.singletonMap("deleted", subscriptionRepository.findById(subscriptionId)), HttpStatus.OK);
    }


}
