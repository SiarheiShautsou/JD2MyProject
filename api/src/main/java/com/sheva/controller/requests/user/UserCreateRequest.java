package com.sheva.controller.requests.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    @Schema(example = "name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String userName;

    @Schema(example = "surname", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String userSurname;

    @Schema(example = "birth", required = true)
    @Pattern(regexp = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @NotBlank
    private Timestamp birth;

    @Schema(example = "country", required = true, minLength = 3, maxLength = 30, type = "string")
    @Size(min = 3, max = 30)
    @NotBlank
    private String userCountry;

    @Schema(example = "city", required = true, minLength = 3, maxLength = 30, type = "string")
    @Size(min = 3, max = 30)
    @NotBlank
    private String userCity;

    @Schema(example = "street", required = true, minLength = 3, maxLength = 30, type = "string")
    @Size(min = 3, max = 30)
    @NotBlank
    private String userStreet;

    @Schema(example = "building", required = true, minLength = 1, maxLength = 5, type = "string")
    @Size(min = 1, max = 5)
    @NotBlank
    private String userBuilding;

    @Schema(description = "male, female, not_selected",
            defaultValue = "not_selected", type = "string")
    private String gender;

    @Schema(example = "login", required = true, minLength = 3, maxLength = 30, type = "string")
    @Size(min = 3, max = 30)
    @NotBlank
    private String userLogin;

    @Schema(example = "password", required = true, minLength = 3, maxLength = 30, type = "string")
    @Size(min = 3, max = 30)
    @NotBlank
    private String userPassword;

    @Email(regexp = "^([a-z0-9_.-]+)@([\\da-z.-]+).([a-z.]{2,6})$")
    @NotBlank
    private String userEmail;

    @Schema(example = "mobile_number", required = true, minLength = 10, maxLength = 20, type = "string")
    @Size(min = 10, max = 20)
    @NotBlank
    private String userMobileNumber;

}
