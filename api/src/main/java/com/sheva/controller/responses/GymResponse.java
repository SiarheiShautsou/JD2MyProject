package com.sheva.controller.responses;

import lombok.Data;

@Data
public class GymResponse {

    private String gymName;

    private String country;

    private String city;

    private String street;

    private String building;

    private Integer square;
}
