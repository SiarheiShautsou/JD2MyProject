package com.sheva.controller.requests.gym;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class GymCreateRequest {

    @Schema(example = "gym_name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String gymName;

    @Schema(example = "country", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String country;

    @Schema(example = "city", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String city;

    @Schema(example = "100", type = "integer")
    @Positive
    private Integer square;

    @Schema(example = "street", required = true, minLength = 2, maxLength = 40, type = "string")
    @Size(min = 2, max = 40)
    @NotBlank
    private String street;

    @Schema(example = "building", required = true, minLength = 1, maxLength = 5, type = "string")
    @Size(min = 1, max = 5)
    @NotBlank
    private String building;

}
