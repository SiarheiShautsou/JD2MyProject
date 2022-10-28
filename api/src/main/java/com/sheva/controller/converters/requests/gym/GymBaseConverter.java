package com.sheva.controller.converters.requests.gym;

import com.sheva.controller.requests.gym.GymCreateRequest;
import com.sheva.domain.Gym;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class GymBaseConverter<S, T> implements Converter<S, T> {

    protected Gym doConvert(Gym gymForUpdate, GymCreateRequest source){

        gymForUpdate.setGymName(source.getGymName());
        gymForUpdate.setCountry(source.getCountry());
        gymForUpdate.setCity(source.getCity());
        gymForUpdate.setSquare(source.getSquare());
        gymForUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        gymForUpdate.setIsDeleted(false);

        return gymForUpdate;
    }
}
