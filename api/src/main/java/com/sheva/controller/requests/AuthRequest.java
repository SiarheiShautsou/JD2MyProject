package com.sheva.controller.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Login request")
public class AuthRequest {

    @Schema(defaultValue = "login", type = "string")
    @NotBlank
    private String login;

    @Schema(defaultValue = "password", type = "string")
    @NotBlank
    private String password;
}
