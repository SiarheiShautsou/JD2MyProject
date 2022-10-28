package com.sheva.controller.responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TrainingResponse {

    private String trainerName;

    private String trainerSurname;

    private Timestamp trainingDate;

    private String trainingTypeName;

}
