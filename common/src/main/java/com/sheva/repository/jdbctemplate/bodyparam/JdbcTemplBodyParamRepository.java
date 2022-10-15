package com.sheva.repository.jdbctemplate.bodyparam;

import com.sheva.domain.BodyParameters;
import com.sheva.domain.User;
import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Builder
public class JdbcTemplBodyParamRepository implements BodyParamRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final BodyParamRowMapper bodyParamRowMapper;

    @Override
    public BodyParameters findById(Long id) {
        return jdbcTemplate.queryForObject("Select * from training_place.body_parameters where user_id = " + id + ";", bodyParamRowMapper);
    }

    @Override
    public List<BodyParameters> findBodyParamByUser(User user) {
        return jdbcTemplate.query("Select * from training_place.body_parameters where user_id = " + user.getId() + ";", bodyParamRowMapper);
    }

    @Override
    public BodyParameters createBodyParamForUser(BodyParameters parameters, User user) {

        final String insertQuery = "insert into training_place.body_parameters (user_id, height, weight, bust, waist, hip, creation_date) " +
                "values (:user_id, :height, :weight, :bust, :waist, :hip, :creation_date);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("user_id", user.getId());
        mapSqlParameterSource.addValue("height", parameters.getHeight());
        mapSqlParameterSource.addValue("weight", parameters.getWeight());
        mapSqlParameterSource.addValue("bust", parameters.getBust());
        mapSqlParameterSource.addValue("waist", parameters.getWaist());
        mapSqlParameterSource.addValue("hip", parameters.getHip());
        mapSqlParameterSource.addValue("creation_date", parameters.getCreationDate());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertParamId = namedParameterJdbcTemplate.query("SELECT currval('training_place.body_parameters_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertParamId);
    }
}
