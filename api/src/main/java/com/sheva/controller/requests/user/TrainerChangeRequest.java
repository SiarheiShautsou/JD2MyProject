package com.sheva.controller.requests.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TrainerChangeRequest extends TrainerCreateRequest {

    private Long id;
}
