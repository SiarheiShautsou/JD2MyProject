package com.sheva.controller.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BodyParamCreateRequest {

    private String userName;

    private String userSurname;

    private Integer height;

    private Double weight;

    private Integer bust;

    private Integer waist;

    private Integer hip;

    private Timestamp creationDate;

}
