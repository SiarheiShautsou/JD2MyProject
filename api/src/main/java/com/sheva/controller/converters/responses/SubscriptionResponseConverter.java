package com.sheva.controller.converters.responses;

import com.sheva.controller.responses.SubscriptionResponse;
import com.sheva.domain.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionResponseConverter implements Converter<Subscription, SubscriptionResponse> {

    @Override
    public SubscriptionResponse convert(Subscription source) {

        SubscriptionResponse response = new SubscriptionResponse();
        response.setUserName(source.getUser().getUserName());
        response.setUserSurname(source.getUser().getUserSurname());
        response.setGymName(source.getGym().getGymName());
        response.setValidFrom(source.getValidFrom());
        response.setValidTo(source.getValidTo());
        response.setIsUnlimited(source.getIsUnlimited());
        response.setTrainingCount(source.getTrainingsCount());
        return response;
    }
}
