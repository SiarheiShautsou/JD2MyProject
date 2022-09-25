package com.sheva.controller;

import com.sheva.controller.requests.UserCreateRequest;
import com.sheva.controller.requests.UserSearchRequest;
import com.sheva.domain.User;
import com.sheva.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<Object> findAllUserWithParams(@ModelAttribute UserSearchRequest userSearchRequest) {

        int verifiedLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.search(verifiedLimit, verifiedOffset);

        Map<String, Object> model = new HashMap<>();
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findUserById(@PathVariable String id) {

        long userId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("user", userService.findById(userId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest userCreateRequest) {

        User user = new User();

        user.setUserName(userCreateRequest.getUserName());
        user.setUserSurname(userCreateRequest.getUserSurname());
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCountry(userCreateRequest.getUserCountry());
        user.setCity(userCreateRequest.getUserCity());
        user.setIsDeleted(false);
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setUserLatitude(1);
        user.setUserLongitude(1);
        user.setMobileNumber(userCreateRequest.getUserMobileNumber());

        userService.create(user);

        List<User> users = userService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("user", user.getUserSurname());
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) {

        Long userid = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("deleted user", userService.deleteUser(userid)), HttpStatus.OK);
    }

}
