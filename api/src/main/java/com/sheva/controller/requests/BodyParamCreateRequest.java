package com.sheva.controller.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class BodyParamCreateRequest {

    @Schema(example = "name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String userName;

    @Schema(example = "surname", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String userSurname;

    @Schema(example = "170", type = "integer")
    @Positive
    private Integer height;

    @Schema(example = "70.0", type = "double")
    @Positive
    private Double weight;

    @Schema(example = "100", type = "integer")
    @Positive
    private Integer bust;

    @Schema(example = "70", type = "integer")
    @Positive
    private Integer waist;

    @Schema(example = "100", type = "integer")
    @Positive
    private Integer hip;

}
