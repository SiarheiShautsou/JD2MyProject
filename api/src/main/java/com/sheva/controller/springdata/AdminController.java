package com.sheva.controller.springdata;

import com.sheva.domain.SystemRoles;
import com.sheva.domain.User;
import com.sheva.domain.UserRole;
import com.sheva.repository.GymSpringDataRepository;
import com.sheva.repository.RoleSpringDataRepository;
import com.sheva.repository.SubscriptionSpringDataRepository;
import com.sheva.repository.UserSpringDataRepository;
import com.sheva.service.body.BodyParamsServiceInterface;
import com.sheva.service.gym.GymServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserSpringDataRepository userRepository;

    private final BodyParamsServiceInterface bodyParamsService;

    private final GymServiceInterface gymService;

    private final RoleSpringDataRepository roleRepository;

    private final GymSpringDataRepository gymRepository;

    private final SubscriptionSpringDataRepository subscriptionRepository;

    @Operation(summary = "Find all users")
    @Parameters({
            @Parameter(name = "X-Auth-Token", required = true, in = ParameterIn.HEADER,
                    schema = @Schema(type = "string", defaultValue = "token")),
            @Parameter(name = "page", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(type = "integer", defaultValue = "1")),
            @Parameter(name = "size", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(type = "integer", defaultValue = "1")),
            @Parameter(name = "sort", in = ParameterIn.QUERY,
                    schema = @Schema(multipleOf = 2, type = "string"))
    })
    @GetMapping("/all-users")
    public ResponseEntity<Object> findAllUsers(Pageable pageable) {

        return new ResponseEntity<>(userRepository.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Find all body parameters")
    @GetMapping("/all-body-parameters")
    public ResponseEntity<Object> findAffBodyParameters(){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("body", bodyParamsService.findAllBodyParameters());

        return new ResponseEntity<>(parameters, HttpStatus.OK);
    }

    @Operation(summary = "Find user by id")
    @GetMapping
    @RequestMapping("/find-user/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable String id) {

        Long userId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("result", userRepository.findById(userId)), HttpStatus.OK);
    }

    @Operation(summary = "Find all gyms")
    @GetMapping("/all-gyms")
    public ResponseEntity<Object> findAllGyms(@ParameterObject Pageable pageable) {
        return new ResponseEntity<>(gymService.findAllGyms(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Add new role for User")
    @PatchMapping("/add-user-role/{id}&{role}")
    @Transactional
    public ResponseEntity<Object> addRoleForUser(@PathVariable("id") String id, @PathVariable("role") String role){

        Long userId = Long.parseLong(id);
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        Set<UserRole> roles = user.getRoles();
        UserRole userRole = roleRepository.findByRoleName(SystemRoles.valueOf(role));
        roles.add(userRole);
        userRepository.flush();

        userRepository.createRoleRow(userRole.getId(), user.getId(),
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(Collections.singletonMap("user" , user), HttpStatus.OK);
    }

    @Operation(summary = "Delete user forever")
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String id) {

        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);

        return new ResponseEntity<>(Collections.singletonMap("user", userRepository.findById(userId)), HttpStatus.OK);
    }

}
