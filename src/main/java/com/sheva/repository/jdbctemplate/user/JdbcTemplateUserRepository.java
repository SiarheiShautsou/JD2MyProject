package com.sheva.repository.jdbctemplate.user;

import com.sheva.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateUserRepository implements UserRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserRowMapper userRowMapper;

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from training_place.users where id = " + id, userRowMapper);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from training_place.users limit " + limit + " offset " + offset, userRowMapper);
    }

    @Override
    public User create(User object) {

        final String insertQuery = "insert into training_place.users (user_name, user_surname, birth, country, city, is_deleted, creation_date, modification_date, user_latitude, user_longitude, mobile_number) " +
                " values (:user_name, :user_surname, :birth, :country, :city, :is_deleted, :creation_date, :modification_date, :user_latitude, :user_longitude, :mobile_number );";

        namedParameterJdbcTemplate.update(insertQuery, sqlParamSourceUserCreator(object));

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('training_place.user_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public User update(User object, Long id) {

        final String updateQuery = "update training_place.users set user_name = :user_name, user_surname = :user_surname, " +
                "birth = :birth, country = :country, city = :city, is_deleted = :is_deleted, creation_date = :creation_date, modification_date = :modification_date, " +
                "user_latitude = :user_latitude, user_longitude = :user_longitude, mobile_number = :mobile_number where id = " + id;

        namedParameterJdbcTemplate.update(updateQuery, sqlParamSourceUserCreator(object));

        return findById(id);

    }

    @Override
    public Long delete(Long id) {

        jdbcTemplate.update("delete from training_place.users where id = " + id);

        return id;
    }

    @Override
    public Map<String, Object> getUserStats() {
        return jdbcTemplate.query("select training_place.get_users_stats_average_weight(false)", resultSet -> {

            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }

    @Override
    public User getUserByFullName(String name, String surname) {

        final String callFunction = "select training_place.get_user_by_fullname(:name, :surname)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", name.substring(0, 3));
        mapSqlParameterSource.addValue("surname", surname.substring(0, 3));

        Long id = namedParameterJdbcTemplate.query(callFunction, mapSqlParameterSource, resultSet -> {

            resultSet.next();
            return resultSet.getLong(1);
        });
        return findById(id);

    }

    @Override
    public List<User> findUserByTrainingType(String trainingType) {

        return jdbcTemplate.query("select * from training_place.users where id = (select trainer_id from training_place.l_trainer_trainer_type as p " +
                "join training_place.training_types as t on p.training_type_id = t.id where training_type like '" + trainingType + "');", userRowMapper);

    }

    public static MapSqlParameterSource sqlParamSourceUserCreator(User object) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("user_name", object.getUserName());
        mapSqlParameterSource.addValue("user_surname", object.getUserSurname());
        mapSqlParameterSource.addValue("birth", object.getBirth());
        mapSqlParameterSource.addValue("country", object.getCountry());
        mapSqlParameterSource.addValue("city", object.getCity());
        mapSqlParameterSource.addValue("is_deleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creation_date", object.getCreationDate());
        mapSqlParameterSource.addValue("modification_date", object.getModificationDate());
        mapSqlParameterSource.addValue("user_latitude", object.getUserLatitude());
        mapSqlParameterSource.addValue("user_longitude", object.getUserLongitude());
        mapSqlParameterSource.addValue("mobile_number", object.getMobileNumber());

        return mapSqlParameterSource;
    }
}

