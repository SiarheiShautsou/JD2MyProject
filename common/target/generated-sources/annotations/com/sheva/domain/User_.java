package com.sheva.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SetAttribute<User, Training> clientTrainings;
	public static volatile SetAttribute<User, Subscription> subscriptions;
	public static volatile SingularAttribute<User, Gender> gender;
	public static volatile SetAttribute<User, Roles> roles;
	public static volatile SingularAttribute<User, UserCredentials> userCredentials;
	public static volatile SingularAttribute<User, Timestamp> birth;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, Timestamp> creationDate;
	public static volatile SetAttribute<User, BodyParameters> bodyParameters;
	public static volatile SingularAttribute<User, Timestamp> modificationDate;
	public static volatile SingularAttribute<User, Boolean> isDeleted;
	public static volatile SetAttribute<User, Training> trainerTrainings;
	public static volatile SingularAttribute<User, UserLocation> location;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Gym> trainerGym;
	public static volatile SingularAttribute<User, String> userSurname;

	public static final String CLIENT_TRAININGS = "clientTrainings";
	public static final String SUBSCRIPTIONS = "subscriptions";
	public static final String GENDER = "gender";
	public static final String ROLES = "roles";
	public static final String USER_CREDENTIALS = "userCredentials";
	public static final String BIRTH = "birth";
	public static final String USER_NAME = "userName";
	public static final String CREATION_DATE = "creationDate";
	public static final String BODY_PARAMETERS = "bodyParameters";
	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String IS_DELETED = "isDeleted";
	public static final String TRAINER_TRAININGS = "trainerTrainings";
	public static final String LOCATION = "location";
	public static final String ID = "id";
	public static final String TRAINER_GYM = "trainerGym";
	public static final String USER_SURNAME = "userSurname";

}

