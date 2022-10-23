package com.sheva.controller.converters.responses;

import com.sheva.controller.responses.ProfileResponse;
import com.sheva.controller.responses.TrainerProfileResponse;
import com.sheva.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TrainerResponseConverter implements Converter<User, TrainerProfileResponse> {

    @Override
    public TrainerProfileResponse convert(User source) {

        TrainerProfileResponse profile = new TrainerProfileResponse();
        profile.setName(source.getUserName());
        profile.setSurname(source.getUserSurname());
        profile.setBirth(source.getBirth());
        profile.setCountry(source.getLocation().getCountry());
        profile.setCity(source.getLocation().getCity());
        profile.setGender(source.getGender().name());
        profile.setMobileNumber(source.getUserCredentials().getMobileNumber());
        profile.setEmail(source.getUserCredentials().getUserEmail());
        profile.setGymName(source.getTrainerGym().getGymName());

        return profile;
    }
}
