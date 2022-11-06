package com.sheva.controller.requests.subscription;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class SubscriptionCreateRequest {

    @Schema(example = "name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String userName;

    @Schema(example = "surname", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String userSurname;

    @Schema(example = "gym_name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String gymName;

    @Schema(example = "valid_from", required = true)
    @Pattern(regexp = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @NotBlank
    private Timestamp validFrom;

    @Schema(example = "valid_to", required = true)
    @Pattern(regexp = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @NotBlank
    private Timestamp validTo;

    @Schema(defaultValue = "false", type = "boolean")
    @NotBlank
    private Boolean isUnlimited;

    private Integer trainingsCount;
}
