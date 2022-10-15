package com.sheva.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Training.class)
public abstract class Training_ {

	public static volatile SingularAttribute<Training, TrainingTypes> trainingType;
	public static volatile SingularAttribute<Training, Timestamp> modificationDate;
	public static volatile SingularAttribute<Training, Timestamp> trainingDate;
	public static volatile SingularAttribute<Training, User> trainer;
	public static volatile SingularAttribute<Training, User> client;
	public static volatile SingularAttribute<Training, Long> id;
	public static volatile SingularAttribute<Training, Timestamp> creationDate;

	public static final String TRAINING_TYPE = "trainingType";
	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String TRAINING_DATE = "trainingDate";
	public static final String TRAINER = "trainer";
	public static final String CLIENT = "client";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";

}

