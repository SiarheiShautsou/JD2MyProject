package com.sheva.controller.converters.requests.user;

import com.sheva.controller.requests.user.UserChangeRequest;
import com.sheva.domain.User;
import com.sheva.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
