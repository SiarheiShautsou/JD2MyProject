package com.sheva.controller.springdata;

import com.sheva.controller.requests.UserCreateRequest;
import com.sheva.domain.Gender;
import com.sheva.domain.SystemRoles;
import com.sheva.domain.User;
import com.sheva.domain.UserCredentials;
import com.sheva.domain.UserLocation;
import com.sheva.repository.springdata.RoleSpringDataRepository;
import com.sheva.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data/user")
public class UserController {

    private final UserSpringDataRepository userRepository;

    private final RoleSpringDataRepository roleRepository;


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
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest request){

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserSurname(request.getUserSurname());
        user.setGender(Gender.valueOf("FEMALE"));
        user.setIsDeleted(false);
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));

        UserLocation location = new UserLocation();
        location.setCountry(request.getUserCountry());
        location.setCity(request.getUserCity());
        location.setLatitude(8494666L);
        location.setLongitude(6846846L);

        user.setLocation(location);

        UserCredentials credentials = new UserCredentials();
        credentials.setUserLogin(request.getUserLogin());
        credentials.setUserPassword(request.getUserPassword());
        credentials.setUserEmail(request.getUserEmail());
        credentials.setMobileNumber(request.getUserMobileNumber());

        user.setUserCredentials(credentials);

        User createdUser = userRepository.save(user);

        userRepository.createRoleRow(roleRepository.findByRoleName(SystemRoles.valueOf("ROLE_TRAINER")).getId(),
                createdUser.getId(),
                new Timestamp(new Date().getTime()),
                new Timestamp(new Date(). getTime()));

        Map<String, Object> model = new HashMap<>();
        model.put("user", createdUser);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @DeleteMapping("/harddelete/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String id){

        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);

        return new ResponseEntity<>(Collections.singletonMap("user", userRepository.findById(userId)), HttpStatus.OK);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> getUserIdDeleted(@PathVariable String id) {

        Long userId = Long.parseLong(id);


        return new ResponseEntity<>(Collections.singletonMap("deleted", userRepository.findById(userId)), HttpStatus.OK);
    }




}
