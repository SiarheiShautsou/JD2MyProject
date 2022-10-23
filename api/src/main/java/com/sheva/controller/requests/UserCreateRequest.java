package com.sheva.controller.requests;

import com.sheva.domain.Gender;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    private String userName;

    private String userSurname;

    private Timestamp birth;

    private String userCountry;

    private String userCity;

    private String userStreet;

    private String userBuilding;

    private String gender;

    private String userLogin;

    private String userPassword;

    private String userEmail;

    private String userMobileNumber;

}
