package com.sheva.controller.responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProfileResponse {

    private String name;

    private String surname;

    private Timestamp birth;

    private String gender;

    private String country;

    private String city;

    private String mobileNumber;

    private String email;
}
