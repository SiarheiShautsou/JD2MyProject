package com.sheva.controller.converters.requests;

import com.sheva.controller.converters.requests.UserBaseConverter;
import com.sheva.controller.requests.UserChangeRequest;
import com.sheva.domain.User;
import com.sheva.repository.springdata.UserSpringDataRepository;
import com.sheva.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangeRequestConverter extends UserBaseConverter<UserChangeRequest, User> {

    private final UserService userService;

    @Override
    public User convert(UserChangeRequest source) {

        User user = userService.findUserById(source.getId());
        return doConvert(user, source);
    }
}
