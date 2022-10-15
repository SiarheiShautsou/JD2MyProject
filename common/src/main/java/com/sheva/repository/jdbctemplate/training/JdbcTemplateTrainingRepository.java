package com.sheva.repository.jdbctemplate.training;

import com.sheva.domain.Training;
import com.sheva.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateTrainingRepository implements TrainingRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final TrainingRowMapper trainingRowMapper;

    @Override
    public Training findById(Long id) {
        return jdbcTemplate.queryForObject("select * from training_place.l_trainings where id =" + id + ";", trainingRowMapper);
    }

    @Override
    public Optional<Training> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Training> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Training> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from training_place.l_trainings limit " + limit + " offset " + offset + ";", trainingRowMapper);
    }

    @Override
    public Training create(Training object) {

        final String insertQuery = "insert into training_place.l_trainings (trainer_id, client_id, training_date, creation_date, modification_date, training_type_id) " +
                " values (:trainer_id, :client_id, :training_date, :creation_date, :modification_date, :training_type_id);";

        namedParameterJdbcTemplate.update(insertQuery, sqlParamSourceTrainingCreator(object));

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('training_place.l_timetable_trainings_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }


    @Override
    public Training update(Training object, Long id) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public List<Training> findTrainingsByUser(User user) {
        return null;
    }

    public static MapSqlParameterSource sqlParamSourceTrainingCreator(Training object) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

//        mapSqlParameterSource.addValue("trainer_id", object.getTrainerId());
//        mapSqlParameterSource.addValue("client_id", object.getClientId());
        mapSqlParameterSource.addValue("training_date", object.getTrainingDate());
        mapSqlParameterSource.addValue("creation_date", object.getCreationDate());
        mapSqlParameterSource.addValue("modification_date", object.getModificationDate());
//        mapSqlParameterSource.addValue("training_type_id", object.getTrainingTypeId());

        return mapSqlParameterSource;
    }
}
