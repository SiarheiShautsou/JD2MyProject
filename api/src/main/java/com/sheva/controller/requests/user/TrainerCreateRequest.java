package com.sheva.controller.requests.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TrainerCreateRequest extends UserCreateRequest {

    @Schema(example = "gym_name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String gymName;
}
