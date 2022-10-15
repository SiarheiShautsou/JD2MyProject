package com.sheva.controller.requests;

import lombok.Data;

@Data
public class SubscriptionCreateRequest {

    private String userName;

    private String userSurname;

    private String gymName;

    private Boolean isUnlimited;

    private Integer trainingsCount;
}
