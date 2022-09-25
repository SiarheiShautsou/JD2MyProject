package com.sheva.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String userName;

    private String userSurname;

    private Timestamp birth;

    private String country;

    private String city;

    private Boolean isDeleted;

    private Timestamp creationDate;

    private Timestamp modificationDate;

    private Integer userLatitude;

    private Integer userLongitude;

    private Long mobileNumber;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

//    public Point userPosition(Integer lon, Integer lat) {
//
//
//
//        return new Point(lon, lat);
//    }
}
