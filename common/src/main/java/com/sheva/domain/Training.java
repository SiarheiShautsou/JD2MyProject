package com.sheva.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@EqualsAndHashCode(exclude = {"trainer", "client", "trainingType"})
@Table(name = "l_trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "training_date")
    private Timestamp trainingDate;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @JsonBackReference
    private User trainer;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private User client;

    @ManyToOne
    @JoinColumn(name = "training_type_id")
    @JsonBackReference
    private TrainingType trainingType;

}
