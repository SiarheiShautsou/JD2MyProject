package com.sheva.controller.converters.responses;

import com.sheva.controller.responses.ProfileResponse;
import com.sheva.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter implements Converter<User, ProfileResponse> {

    @Override
    public ProfileResponse convert(User source) {

        ProfileResponse profile = new ProfileResponse();
        profile.setName(source.getUserName());
        profile.setSurname(source.getUserSurname());
        profile.setBirth(source.getBirth());
        profile.setCountry(source.getLocation().getCountry());
        profile.setCity(source.getLocation().getCity());
        profile.setGender(source.getGender().name());
        profile.setMobileNumber(source.getUserCredentials().getMobileNumber());
        profile.setEmail(source.getUserCredentials().getUserEmail());

        return profile;
    }

}
