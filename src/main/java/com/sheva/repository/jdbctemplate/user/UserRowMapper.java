package com.sheva.repository.jdbctemplate.user;

import com.sheva.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sheva.repository.jdbctemplate.user.UserTableColumns.BIRTH;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.CITY;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.COUNTRY;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.CREATION_DATE;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.ID;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.IS_DELETED;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.MOBILE_NUMBER;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.MODIFICATION_DATE;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.USER_LATITUDE;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.USER_LONGITUDE;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.USER_NAME;
import static com.sheva.repository.jdbctemplate.user.UserTableColumns.USER_SURNAME;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();

        user.setId(resultSet.getLong(ID));
        user.setUserName(resultSet.getString(USER_NAME));
        user.setUserSurname(resultSet.getString(USER_SURNAME));
        user.setBirth(resultSet.getTimestamp(BIRTH));
        user.setCountry(resultSet.getString(COUNTRY));
        user.setCity(resultSet.getString(CITY));
        user.setIsDeleted(resultSet.getBoolean(IS_DELETED));
        user.setCreationDate(resultSet.getTimestamp(CREATION_DATE));
        user.setModificationDate(resultSet.getTimestamp(MODIFICATION_DATE));
        user.setUserLatitude(resultSet.getInt(USER_LATITUDE));
        user.setUserLongitude(resultSet.getInt(USER_LONGITUDE));
        user.setMobileNumber(resultSet.getLong(MOBILE_NUMBER));


        return user;
    }
}
