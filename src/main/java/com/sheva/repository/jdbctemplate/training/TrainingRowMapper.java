package com.sheva.repository.jdbctemplate.training;

import com.sheva.domain.Training;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.ID;
import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.TRAINER_ID;
import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.CLIENT_ID;
import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.TRAINING_DATE;
import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.CREATION_DATE;
import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.MODIFICATION_DATE;
import static com.sheva.repository.jdbctemplate.training.TrainingTableColumns.TRAINING_TYPE_ID;

@Component
public class TrainingRowMapper implements RowMapper<Training> {
    @Override
    public Training mapRow(ResultSet rs, int rowNum) throws SQLException {

        Training training = new Training();

        training.setId(rs.getLong(ID));
        training.setTrainerId(rs.getLong(TRAINER_ID));
        training.setClientId(rs.getLong(CLIENT_ID));
        training.setTrainingDate(rs.getTimestamp(TRAINING_DATE));
        training.setCreationDate(rs.getTimestamp(CREATION_DATE));
        training.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));
        training.setTrainingTypeId(rs.getInt(TRAINING_TYPE_ID));


        return training;
    }
}
