package com.sheva.repository.jdbctemplate.gym;

import com.sheva.domain.Gym;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateGymRepository implements GymRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final GymRowMapper gymRowMapper;

    @Override
    public Gym findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from training_place.gyms where id = " + id + ";", gymRowMapper);
    }

    @Override
    public Optional<Gym> findOne(Integer id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Gym> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Gym> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from training_place.gyms limit " + limit + " offset " + offset, gymRowMapper);
    }

    @Override
    public Gym create(Gym object) {

        final String insertQuery = "insert into training_place.gyms (gym_name, country, city, square, creation_date, modification_date, gym_latitude, gym_longitude, is_deleted) " +
                " values (:gym_name, :country, :city, :square, :creation_date, :modification_date, :gym_latitude, :gym_longitude, :is_deleted );";

        namedParameterJdbcTemplate.update(insertQuery, sqlParamSourceCreatorGym(object));

        Integer lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('training_place.gyms_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getInt("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public Gym update(Gym object, Integer id) {

        final String updateQuery = "update training_place.gyms set gym_name = :gym_name, country = :country, city = :city, " +
                "square = :square, creation_date = :creation_date, modification_date = :modification_date, gym_latitude = :gym_latitude, gym_longitude = :gym_longitude, is_deleted = :is_deleted where id = " + id + ";";

        namedParameterJdbcTemplate.update(updateQuery, sqlParamSourceCreatorGym(object));

        return findById(id);
    }

    @Override
    public Integer delete(Integer id) {
        return jdbcTemplate.update("delete from training_place.gyms where id = " + id);
    }

    @Override
    public Map<String, Object> getGymStats() {
        return null;
    }

    @Override
    public List<Gym> findGymByCity(String city) {
        return jdbcTemplate.query("select * from gyms where city = " + city + ";", gymRowMapper);
    }

    public static MapSqlParameterSource sqlParamSourceCreatorGym(Gym object) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        mapSqlParameterSource.addValue("gym_name", object.getGymName());
        mapSqlParameterSource.addValue("country", object.getCountry());
        mapSqlParameterSource.addValue("city", object.getCity());
        mapSqlParameterSource.addValue("square", object.getSquare());
        mapSqlParameterSource.addValue("creation_date", object.getCreationDate());
        mapSqlParameterSource.addValue("modification_date", object.getModificationDate());
        mapSqlParameterSource.addValue("gym_latitude", object.getGymLatitude());
        mapSqlParameterSource.addValue("gym_longitude", object.getGymLongitude());
        mapSqlParameterSource.addValue("is_deleted", object.getIsDeleted());

        return mapSqlParameterSource;
    }
}
