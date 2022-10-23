package com.sheva.controller.requests;

import lombok.Data;

@Data
public class TrainerCreateRequest extends UserCreateRequest{

    private String gymName;
}
