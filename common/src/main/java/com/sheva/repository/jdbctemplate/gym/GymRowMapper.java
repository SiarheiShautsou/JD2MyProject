package com.sheva.repository.jdbctemplate.gym;

import com.sheva.domain.Gym;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.ID;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.GYM_NAME;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.COUNTRY;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.CITY;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.IS_DELETED;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.SQUARE;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.CREATION_DATE;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.MODIFICATION_DATE;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.GYM_LATITUDE;
import static com.sheva.repository.jdbctemplate.gym.GymTableColumns.GYM_LONGITUDE;

@Component
public class GymRowMapper implements RowMapper<Gym> {
    @Override
    public Gym mapRow(ResultSet rs, int rowNum) throws SQLException {

        Gym gym = new Gym();

        gym.setId(rs.getInt(ID));
        gym.setGymName(rs.getString(GYM_NAME));
        gym.setCountry(rs.getString(COUNTRY));
        gym.setCity(rs.getString(CITY));
        gym.setSquare(rs.getInt(SQUARE));
        gym.setCreationDate(rs.getTimestamp(CREATION_DATE));
        gym.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));
        gym.setGymLatitude(rs.getInt(GYM_LATITUDE));
        gym.setGymLongitude(rs.getInt(GYM_LONGITUDE));
        gym.setIsDeleted(rs.getBoolean(IS_DELETED));

        return gym;
    }
}
