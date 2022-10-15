package com.sheva.controller.springdata;

import com.sheva.repository.springdata.RoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/roles")
public class RoleController {

    private final RoleSpringDataRepository roleRepository;

    @GetMapping()
    public ResponseEntity<Object> findAllRoles(){
        return new ResponseEntity<>(Collections.singletonMap("result", roleRepository.findAll()), HttpStatus.OK);
    }

}
