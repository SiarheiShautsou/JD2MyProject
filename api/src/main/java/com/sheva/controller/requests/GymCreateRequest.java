package com.sheva.controller.requests;

import lombok.Data;

@Data
public class GymCreateRequest {

    private String gymName;

    private String country;

    private String city;

    private String square;

}
