package com.sheva.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrainingTypes.class)
public abstract class TrainingTypes_ {

	public static volatile SingularAttribute<TrainingTypes, String> trainingType;
	public static volatile SetAttribute<TrainingTypes, Training> trainings;
	public static volatile SingularAttribute<TrainingTypes, Integer> id;

	public static final String TRAINING_TYPE = "trainingType";
	public static final String TRAININGS = "trainings";
	public static final String ID = "id";

}

