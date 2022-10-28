package com.sheva.controller.requests.user;

import com.sheva.controller.requests.user.UserCreateRequest;
import lombok.Data;

@Data
public class TrainerCreateRequest extends UserCreateRequest {

    private String gymName;
}
