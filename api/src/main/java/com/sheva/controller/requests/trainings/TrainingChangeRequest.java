package com.sheva.controller.requests.trainings;

import com.sheva.controller.requests.trainings.TrainingCreateRequest;
import lombok.Data;

@Data
public class TrainingChangeRequest extends TrainingCreateRequest {

    private Long trainingId;
}
