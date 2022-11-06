package com.sheva.controller.requests.trainings;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class TrainingCreateRequest {

    @Schema(example = "client_name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String clientName;

    @Schema(example = "client_surname", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String clientSurname;

    @Schema(example = "trainer_name", required = true, minLength = 2, maxLength = 20, type = "string")
    @Size(min = 2, max = 20)
    @NotBlank
    private String trainerName;

    @Schema(example = "trainer_surname", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String trainerSurname;

    @Schema(example = "training_date", required = true)
    @Pattern(regexp = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @NotBlank
    private Timestamp trainingDate;

    @Schema(example = "training_type_name", required = true, minLength = 2, maxLength = 30, type = "string")
    @Size(min = 2, max = 30)
    @NotBlank
    private String trainingTypeName;


}
