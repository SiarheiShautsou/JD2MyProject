package com.sheva.controller.springdata;

import com.sheva.domain.UserRole;
import com.sheva.repository.RoleSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/roles")
public class RoleController {

    private final RoleSpringDataRepository roleRepository;

    @Operation(summary = "Find all roles")
    @GetMapping()
    public ResponseEntity<Object> findAllRoles(){
        return new ResponseEntity<>(Collections.singletonMap("result", roleRepository.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Find a role by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findRoleById(@PathVariable final String id){

        Long roleId = Long.parseLong(id);
        UserRole role = roleRepository.findById(roleId).get();

        Map<String, Object> model = new HashMap<>();
        model.put("role", role);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @Operation(summary = "Delete a role by Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRoleById(@PathVariable final String id){

        Long roleId = Long.parseLong(id);
        UserRole role = roleRepository.findById(roleId).get();
        roleRepository.deleteById(roleId);

        Map<String, Object> model = new HashMap<>();
        model.put("deleted role", role);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

}
