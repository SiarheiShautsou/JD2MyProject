package com.sheva.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subscription.class)
public abstract class Subscription_ {

	public static volatile SingularAttribute<Subscription, Boolean> isUnlimited;
	public static volatile SingularAttribute<Subscription, Gym> gym;
	public static volatile SingularAttribute<Subscription, Long> id;
	public static volatile SingularAttribute<Subscription, Timestamp> validFrom;
	public static volatile SingularAttribute<Subscription, Integer> trainingsCount;
	public static volatile SingularAttribute<Subscription, User> user;
	public static volatile SingularAttribute<Subscription, Timestamp> validTo;

	public static final String IS_UNLIMITED = "isUnlimited";
	public static final String GYM = "gym";
	public static final String ID = "id";
	public static final String VALID_FROM = "validFrom";
	public static final String TRAININGS_COUNT = "trainingsCount";
	public static final String USER = "user";
	public static final String VALID_TO = "validTo";

}

