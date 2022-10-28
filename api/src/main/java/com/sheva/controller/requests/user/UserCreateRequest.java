package com.sheva.controller.requests.user;

import com.sheva.domain.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
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

    @Email(regexp = "^([a-z0-9_.-]+)@([\\da-z.-]+).([a-z.]{2,6})$")
    private String userEmail;

    private String userMobileNumber;

}
