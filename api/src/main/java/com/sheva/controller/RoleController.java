package com.sheva.controller;

import com.sheva.domain.UserRole;
import com.sheva.exception.NonSuchEntityException;
import com.sheva.repository.RoleSpringDataRepository;
import com.sheva.util.MapGenerator;
import com.sheva.util.UUIDGenerator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("data/roles")
public class RoleController {

    private final RoleSpringDataRepository roleRepository;

    @Operation(summary = "Find all roles")
    @GetMapping()
    public ResponseEntity<Object> findAllRoles() {
        return new ResponseEntity<>(Collections.singletonMap("result", roleRepository.findAll()), HttpStatus.OK);
    }

    @Operation(summary = "Find a role by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findRoleById(@PathVariable final String id) {

        UserRole userRole = searchRole(id);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(userRole), HttpStatus.OK);
    }

    @Operation(summary = "Delete a role by Id")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteRoleById(@PathVariable final String id) {

        UserRole deleteRole = searchRole(id);
        roleRepository.delete(deleteRole);

        return new ResponseEntity<>(MapGenerator.convertObjectToMap(deleteRole), HttpStatus.OK);

    }

    private UserRole searchRole(String id) {
        Long roleId = Long.parseLong(id);
        Optional<UserRole> result = roleRepository.findById(roleId);
        UserRole role;
        if (result.isPresent()) {
            role = result.get();
        } else {
            throw new NonSuchEntityException(
                    (String.format("Role with id %s not found", roleId)), 404, UUIDGenerator.generateUUID());
        }
        return role;
    }

}
