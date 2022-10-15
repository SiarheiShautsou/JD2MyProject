package com.sheva.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Gym.class)
public abstract class Gym_ {

	public static volatile SingularAttribute<Gym, String> country;
	public static volatile SingularAttribute<Gym, Integer> square;
	public static volatile SetAttribute<Gym, User> trainers;
	public static volatile SingularAttribute<Gym, Timestamp> modificationDate;
	public static volatile SetAttribute<Gym, Subscription> subscriptions;
	public static volatile SingularAttribute<Gym, Integer> gymLatitude;
	public static volatile SingularAttribute<Gym, Boolean> isDeleted;
	public static volatile SingularAttribute<Gym, String> city;
	public static volatile SingularAttribute<Gym, String> gymName;
	public static volatile SingularAttribute<Gym, Integer> id;
	public static volatile SingularAttribute<Gym, Timestamp> creationDate;
	public static volatile SingularAttribute<Gym, Integer> gymLongitude;

	public static final String COUNTRY = "country";
	public static final String SQUARE = "square";
	public static final String TRAINERS = "trainers";
	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String SUBSCRIPTIONS = "subscriptions";
	public static final String GYM_LATITUDE = "gymLatitude";
	public static final String IS_DELETED = "isDeleted";
	public static final String CITY = "city";
	public static final String GYM_NAME = "gymName";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String GYM_LONGITUDE = "gymLongitude";

}

