package com.sheva.controller.requests.subscription;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class SubscriptionCreateRequest {

    private String userName;

    private String userSurname;

    private String gymName;

    private Timestamp validFrom;

    private Timestamp validTo;

    private Boolean isUnlimited;

    private Integer trainingsCount;
}
