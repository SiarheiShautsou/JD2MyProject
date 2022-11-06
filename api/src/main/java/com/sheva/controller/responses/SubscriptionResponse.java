package com.sheva.controller.responses;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubscriptionResponse {

    private String userName;

    private String userSurname;

    private String gymName;

    private Timestamp validFrom;

    private Timestamp validTo;

    private Boolean isUnlimited;

    private Integer trainingCount;
}
