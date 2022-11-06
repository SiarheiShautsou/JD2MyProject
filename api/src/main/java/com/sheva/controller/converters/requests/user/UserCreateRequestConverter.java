package com.sheva.controller.converters.requests.user;

import com.sheva.controller.converters.requests.user.UserBaseConverter;
import com.sheva.controller.requests.user.UserCreateRequest;
import com.sheva.domain.User;
import com.sheva.domain.UserCredentials;
import com.sheva.domain.UserLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UserBaseConverter<UserCreateRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserCreateRequest source) {

        User user = new User();
        user.setCreationDate(new Timestamp(new Date().getTime()));

        UserLocation location = new UserLocation();
        location.setCountry(source.getUserCountry());
        location.setCity(source.getUserCity());
        location.setStreet(source.getUserStreet());
        location.setBuilding(source.getUserBuilding());

        user.setLocation(location);

        UserCredentials credentials = new UserCredentials();
        credentials.setUserLogin(source.getUserLogin());
        credentials.setUserPassword(passwordEncoder.encode(source.getUserPassword()));
        credentials.setUserEmail(source.getUserEmail());
        credentials.setMobileNumber(source.getUserMobileNumber());

        user.setUserCredentials(credentials);

        return doConvert(user, source);

    }
}
