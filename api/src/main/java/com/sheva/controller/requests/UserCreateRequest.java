package com.sheva.controller.requests;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String userName;

    private String userSurname;

    private String userCountry;

    private String userCity;

    private String userGender;

    private String userLogin;

    private String userPassword;

    private String userEmail;

    private String userMobileNumber;

    private String userRole;
}
