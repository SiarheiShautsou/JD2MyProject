package com.sheva.controller.springdata;

import com.sheva.controller.requests.BodyParamCreateRequest;
import com.sheva.controller.responses.BodyParametersResponse;
import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import com.sheva.repository.BodyParamSpringDataRepository;
import com.sheva.repository.UserSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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

    private final BodyParamSpringDataRepository bodyRepository;

    private final UserSpringDataRepository userRepository;

    @Operation(summary = "Find all body parameters")
    @GetMapping
    public ResponseEntity<Object> findAffBodyParameters(){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("body", bodyRepository.findAll());

        return new ResponseEntity<>(parameters, HttpStatus.OK);
    }

    @Operation(summary = "Find user's body parameters")
    @GetMapping("/user/{name}&{surname}")
    public ResponseEntity<Object> findUserBodyParameters(@PathVariable("name") String name, @PathVariable("surname") String surname){

        User user = userRepository.findUserByUserNameAndUserSurname(name, surname).orElse(null);
        List<BodyParameters> userParameters = bodyRepository.findAllByUser(user);
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
    public ResponseEntity<Object> createBodyParameters(@RequestBody BodyParamCreateRequest request){

        User user = userRepository.findUserByUserNameAndUserSurname(request.getUserName(), request.getUserSurname()).get();

        BodyParameters bodyParameter = new BodyParameters();
        bodyParameter.setUser(user);
        bodyParameter.setHeight(request.getHeight());
        bodyParameter.setWeight(request.getWeight());
        bodyParameter.setBust(request.getBust());
        bodyParameter.setWaist(request.getWaist());
        bodyParameter.setHip(request.getHip());
        bodyParameter.setCreationDate(request.getCreationDate());

        BodyParameters createdBodyParam = bodyRepository.save(bodyParameter);

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("parameter", bodyRepository.findById(createdBodyParam.getId()));

        return new ResponseEntity<>(parameter, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete body parameters")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteBodyParameters(@PathVariable String id){

        Long bodyParamId = Long.parseLong(id);
        BodyParameters bodyParam = bodyRepository.findById(bodyParamId).orElse(null);
        bodyRepository.deleteById(bodyParamId);

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("deleted parameters", bodyParam);

        return new ResponseEntity<>(parameter, HttpStatus.OK);
    }

}
