package com.sheva.repository.jdbctemplate.bodyparam;

import com.sheva.domain.BodyParameters;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.ID;
import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.HEIGHT;
import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.WEIGHT;
import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.BUST;
import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.WAIST;
import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.HIP;
import static com.sheva.repository.jdbctemplate.bodyparam.BodyParamTableColumns.CREATION_DATE;

@Component
public class BodyParamRowMapper implements RowMapper<BodyParameters> {


    @Override
    public BodyParameters mapRow(ResultSet rs, int rowNum) throws SQLException {

        BodyParameters parameters = new BodyParameters();

        parameters.setId(rs.getLong(ID));
//        parameters.setUserId(rs.getLong(USER_ID));
        parameters.setHeight(rs.getInt(HEIGHT));
        parameters.setWeight(rs.getInt(WEIGHT));
        parameters.setBust(rs.getInt(BUST));
        parameters.setWaist(rs.getInt(WAIST));
        parameters.setHip(rs.getInt(HIP));
        parameters.setCreationDate(rs.getTimestamp(CREATION_DATE));

        return parameters;
    }
}
