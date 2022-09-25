package com.sheva.controller.requests;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String userName;

    private String userSurname;

    private String userCountry;

    private String userCity;

    private Long userMobileNumber;
}
