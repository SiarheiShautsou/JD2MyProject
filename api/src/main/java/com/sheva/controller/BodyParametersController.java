package com.sheva.controller;

import com.sheva.controller.requests.BodyParamCreateRequest;
import com.sheva.controller.responses.BodyParametersResponse;
import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import com.sheva.service.body_parameters.BodyParamsServiceInterface;
import com.sheva.service.user.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/body-params")
public class BodyParametersController {

    private final BodyParamsServiceInterface bodyParamsService;

    private final UserServiceInterface userService;

    private final ConversionService converter;


    @Operation(summary = "Find user's body parameters")
    @GetMapping("/user/{name}&{surname}")
    public ResponseEntity<Object> findUserBodyParameters(@PathVariable("name") String name, @PathVariable("surname") String surname) {

        User user = userService.findUserByNameAndSurname(name, surname);
        List<BodyParameters> userParameters = bodyParamsService.findAllUserBodyParameters(user);
        List<BodyParametersResponse> responses = new ArrayList<>();

        for (BodyParameters userParameter : userParameters) {
            BodyParametersResponse response = new BodyParametersResponse();
            response.setUserName(userParameter.getUser().getUserName());
            response.setUserSurname(userParameter.getUser().getUserSurname());
            response.setHeight(userParameter.getHeight());
            response.setWeight(userParameter.getWeight());
            response.setBust(userParameter.getBust());
            response.setWaist(userParameter.getWaist());
            response.setHip(userParameter.getHip());
            response.setCreationDate(userParameter.getCreationDate());
            responses.add(response);
        }
        Map<String, Object> model = new HashMap<>();
        model.put("user result", responses);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Operation(summary = "Add new body parameters for User")
    @PostMapping
    @Transactional
    public ResponseEntity<Object> createBodyParameters(@RequestBody BodyParamCreateRequest request) {

        BodyParameters bodyParameter = converter.convert(request, BodyParameters.class);

        BodyParameters createdBodyParam = bodyParamsService.addNewBodyParameters(bodyParameter);

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("parameter", bodyParamsService.findBodyParametersById(createdBodyParam.getId()));

        return new ResponseEntity<>(parameter, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete body parameters")
    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteBodyParameters(@PathVariable String id) {

        Long bodyParamId = Long.parseLong(id);
        BodyParameters bodyParams = bodyParamsService.findBodyParametersById(bodyParamId);
        bodyParamsService.deleteBodyParameters(bodyParams);

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("deleted parameters", bodyParams);

        return new ResponseEntity<>(parameter, HttpStatus.OK);
    }

}
