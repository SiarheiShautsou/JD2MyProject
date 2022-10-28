package com.sheva.controller.requests.gym;

import lombok.Data;

@Data
public class GymCreateRequest {

    private String gymName;

    private String country;

    private String city;

    private Integer square;

}
