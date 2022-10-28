package com.sheva.controller.requests.trainings;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TrainingCreateRequest {

    private String clientName;

    private String clientSurname;

    private String trainerName;

    private String trainerSurname;

    private Timestamp trainingDate;

    private String trainingTypeName;


}
