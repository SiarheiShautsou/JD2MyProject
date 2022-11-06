package com.sheva.controller.requests.trainings;

import lombok.Data;

@Data
public class TrainingChangeRequest extends TrainingCreateRequest {

    private Long trainingId;
}
