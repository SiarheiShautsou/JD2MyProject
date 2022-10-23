package com.sheva.controller.springdata;

import com.sheva.controller.requests.TrainerCreateRequest;
import com.sheva.controller.requests.UserChangeRequest;
import com.sheva.controller.requests.UserCreateRequest;
import com.sheva.controller.responses.ProfileResponse;
import com.sheva.controller.responses.TrainerProfileResponse;
import com.sheva.domain.Gym;
import com.sheva.domain.SystemRoles;
import com.sheva.domain.User;
import com.sheva.domain.UserRole;
import com.sheva.repository.springdata.GymSpringDataRepository;
import com.sheva.repository.springdata.RoleSpringDataRepository;
import com.sheva.repository.springdata.UserSpringDataRepository;
import com.sheva.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/data/user")
public class UserController {

    private final UserSpringDataRepository userRepository;

    private final RoleSpringDataRepository roleRepository;

    private final GymSpringDataRepository gymRepository;

    private final UserService userService;

    private final ConversionService converter;

    @Operation(summary = "Search user by name and surname")
    @GetMapping("/with")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse findUserByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {

        User user = userRepository.findUserByUserNameAndUserSurname(name, surname).orElse(null);

        return converter.convert(user, ProfileResponse.class);
    }

    @Operation(summary = "Search trainers in city")
    @GetMapping("/search-trainers/{city}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainerProfileResponse> searchTrainersByCity(@PathVariable("city") String city) {

        List<TrainerProfileResponse> result = new ArrayList<>();
        List<User> trainers = userService.findAllTrainersInCity(city);
        for (User trainer : trainers) {
                    TrainerProfileResponse profile = converter.convert(trainer, TrainerProfileResponse.class);
                    result.add(profile);
        }
        return result;
    }

    @Operation(summary = "Client registration")
    @PostMapping("/create-client")
    public ResponseEntity<Object> createClient(@RequestBody UserCreateRequest createRequest) {

        User client = converter.convert(createRequest, User.class);

        User createdClient = userService.createClientAccount(client);
        Map<String, Object> model = new HashMap<>();
        model.put("result", createdClient);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @Operation(summary = "Trainer registration")
    @PostMapping("/create-trainer")
    @Transactional
    public ResponseEntity<Object> createTrainer(@RequestBody TrainerCreateRequest createRequest) {

        User trainer = converter.convert(createRequest, User.class);

        User createdTrainer = userService.createTrainerAccount(trainer, createRequest.getGymName());

        Map<String, Object> model = new HashMap<>();
        model.put("user", createdTrainer);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @Operation(summary = "Update user")
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Object> changeUserInfo(@RequestBody UserChangeRequest changeRequest){

        User user = converter.convert(changeRequest, User.class);

        assert user != null;
        User updatedUser = userRepository.save(user);

        Map<String, Object> model = new HashMap<>();
        model.put("user", updatedUser);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }


    @Operation(summary = "Delete user")
    @PatchMapping("/is-deleted/{id}")
    public ResponseEntity<Object> getUserIdDeletedStatus(@PathVariable String id) {

        Long userId = Long.parseLong(id);
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        user.setIsDeleted(true);
        userRepository.save(user);

        return new ResponseEntity<>(Collections.singletonMap("deleted", userRepository.findById(userId)), HttpStatus.OK);
    }


    @Operation(summary = "Delete user forever")
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String id) {

        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);

        return new ResponseEntity<>(Collections.singletonMap("user", userRepository.findById(userId)), HttpStatus.OK);
    }


}
