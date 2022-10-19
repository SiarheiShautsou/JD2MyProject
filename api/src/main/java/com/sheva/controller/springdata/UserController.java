package com.sheva.controller.springdata;

import com.sheva.controller.converters.UserCreateRequestConverter;
import com.sheva.controller.requests.UserCreateRequest;
import com.sheva.domain.Gender;
import com.sheva.domain.Roles;
import com.sheva.domain.SystemRoles;
import com.sheva.domain.User;
import com.sheva.domain.UserCredentials;
import com.sheva.domain.UserLocation;
import com.sheva.repository.springdata.RoleSpringDataRepository;
import com.sheva.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data/user")
public class UserController {

    private final UserSpringDataRepository userRepository;

    private final RoleSpringDataRepository roleRepository;

    private final ConversionService converter;

    @GetMapping("/all")
    public ResponseEntity<Object> findAllUsers(){

        return new ResponseEntity<>(Collections.singletonMap("result",
                userRepository.findByHQLQuery()), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{/id}")
    public ResponseEntity<Object> findUserById(@PathVariable String id){

        Long userId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("result", userRepository.findById(userId)), HttpStatus.OK);
    }

    @GetMapping("/with")
    public ResponseEntity<Object> findUserByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname){

        return new ResponseEntity<>(Collections.singletonMap("result", userRepository.findUserByUserNameAndUserSurname(name, surname)), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest){

        User user = converter.convert(createRequest, User.class);

        User createdUser = userRepository.save(setRole(user));

        Map<String, Object> model = new HashMap<>();
        model.put("user", userRepository.findById(createdUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private User setRole(User user){
        Set<Roles> roles = user.getRoles();

        Set<Roles> updatedRoles = new HashSet<>();
        if(!CollectionUtils.isEmpty(roles)){
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(roleRepository.findById(2L).get());

        user.setRoles(updatedRoles);
        return user;
    }

    @DeleteMapping("/harddelete/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String id){

        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);

        return new ResponseEntity<>(Collections.singletonMap("user", userRepository.findById(userId)), HttpStatus.OK);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> getUserIdDeletedStatus(@PathVariable String id) {

        Long userId = Long.parseLong(id);
        User user = userRepository.findById(userId).get();
        user.setIsDeleted(true);
        userRepository.save(user);

        return new ResponseEntity<>(Collections.singletonMap("deleted", userRepository.findById(userId)), HttpStatus.OK);
    }




}
