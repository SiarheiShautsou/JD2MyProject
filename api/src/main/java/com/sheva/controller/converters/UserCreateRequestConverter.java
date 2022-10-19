package com.sheva.controller.converters;

import com.sheva.controller.requests.UserCreateRequest;
import com.sheva.domain.User;
import com.sheva.domain.UserCredentials;
import com.sheva.domain.UserLocation;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.sql.Timestamp;
import java.util.Date;


public class UserCreateRequestConverter extends UserBaseConverter<UserCreateRequest, User> {

    @Override
    public User convert(UserCreateRequest source) {

        User user = new User();
        user.setCreationDate(new Timestamp(new Date().getTime()));

        UserLocation location = new UserLocation();
        location.setCountry(source.getUserCountry());
        location.setCity(source.getUserCity());
        location.setLatitude(8494666L);
        location.setLongitude(6846846L);

        user.setLocation(location);

        UserCredentials credentials = new UserCredentials();
        credentials.setUserLogin(source.getUserLogin());
        credentials.setUserPassword(source.getUserPassword());
        credentials.setUserEmail(source.getUserEmail());
        credentials.setMobileNumber(source.getUserMobileNumber());

        user.setUserCredentials(credentials);

        return doConvert(user, source);

    }
}
