package com.sheva.controller;

import com.sheva.domain.SystemRoles;
import com.sheva.domain.User;
import com.sheva.domain.UserRole;
import com.sheva.exception.NonSuchEntityException;
import com.sheva.repository.RoleSpringDataRepository;
import com.sheva.repository.SubscriptionSpringDataRepository;
import com.sheva.repository.UserSpringDataRepository;
import com.sheva.service.body_parameters.BodyParamsServiceInterface;
import com.sheva.service.gym.GymServiceInterface;
import com.sheva.util.UUIDGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserSpringDataRepository userRepository;

    private final BodyParamsServiceInterface bodyParamsService;

    private final GymServiceInterface gymService;

    private final RoleSpringDataRepository roleRepository;


    private final SubscriptionSpringDataRepository subscriptionRepository;

    @Operation(summary = "Find all users")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Parameter(
            in = ParameterIn.QUERY,
            description =
                    "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.",
            name = "sort",
            array = @ArraySchema(schema = @Schema(type = "string")))
    @GetMapping("/all-users")
    public ResponseEntity<Object> findAllUsers(@ParameterObject Pageable pageable) {

        return new ResponseEntity<>(userRepository.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Find all body parameters")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @GetMapping("/all-body-parameters")
    public ResponseEntity<Object> findAffBodyParameters() {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("body", bodyParamsService.findAllBodyParameters());

        return new ResponseEntity<>(parameters, HttpStatus.OK);
    }

    @Operation(summary = "Find all subscriptions")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Parameter(
            in = ParameterIn.QUERY,
            description =
                    "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.",
            name = "sort",
            array = @ArraySchema(schema = @Schema(type = "string")))
    @GetMapping("/all-subscriptions")
    public ResponseEntity<Object> findAllSubscriptions(Pageable pageable) {

        return new ResponseEntity<>(subscriptionRepository.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Find user by id")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @GetMapping
    @RequestMapping("/find-user/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable String id) {

        Long userId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("result", userRepository.findById(userId)), HttpStatus.OK);
    }

    @Operation(summary = "Find all gyms")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Parameter(
            in = ParameterIn.QUERY,
            description =
                    "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.",
            name = "sort",
            array = @ArraySchema(schema = @Schema(type = "string")))
    @GetMapping("/all-gyms")
    public ResponseEntity<Object> findAllGyms(@ParameterObject Pageable pageable) {
        return new ResponseEntity<>(gymService.findAllGyms(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Add new role for User")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @PatchMapping("/add-user-role/{id}&{role}")
    @Transactional
    public ResponseEntity<Object> addRoleForUser(@PathVariable("id") String id, @PathVariable("role") String role) {

        Long userId = Long.parseLong(id);
        Optional<User> result = userRepository.findById(userId);
        User user;
        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new NonSuchEntityException(
                    (String.format("User %s not found", userId)), 404, UUIDGenerator.generateUUID());
        }

        Set<UserRole> roles = user.getRoles();
        UserRole userRole = roleRepository.findByRoleName(SystemRoles.valueOf(role));
        roles.add(userRole);
        User userWithNewRole = userRepository.save(user);

        userRepository.createRoleRow(userRole.getId(), user.getId(),
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(Collections.singletonMap("user", userWithNewRole), HttpStatus.OK);
    }

    @Operation(summary = "Delete user forever")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String id) {

        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);

        return new ResponseEntity<>(Collections.singletonMap("user", userRepository.findById(userId)), HttpStatus.OK);
    }

}
