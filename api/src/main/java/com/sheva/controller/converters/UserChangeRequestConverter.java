package com.sheva.controller.converters;

import com.sheva.controller.requests.UserChangeRequest;
import com.sheva.domain.User;
import com.sheva.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChangeRequestConverter extends UserBaseConverter<UserChangeRequest, User>{

    private final UserSpringDataRepository userRepository;

    @Override
    public User convert(UserChangeRequest source) {

        Optional<User> user = userRepository.findById(source.getId());
        return doConvert(user.get(), source);
    }
}
