package com.sheva.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BodyParameters.class)
public abstract class BodyParameters_ {

	public static volatile SingularAttribute<BodyParameters, Integer> weight;
	public static volatile SingularAttribute<BodyParameters, Integer> bust;
	public static volatile SingularAttribute<BodyParameters, Integer> waist;
	public static volatile SingularAttribute<BodyParameters, Long> id;
	public static volatile SingularAttribute<BodyParameters, Timestamp> creationDate;
	public static volatile SingularAttribute<BodyParameters, User> user;
	public static volatile SingularAttribute<BodyParameters, Integer> hip;
	public static volatile SingularAttribute<BodyParameters, Integer> height;

	public static final String WEIGHT = "weight";
	public static final String BUST = "bust";
	public static final String WAIST = "waist";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String USER = "user";
	public static final String HIP = "hip";
	public static final String HEIGHT = "height";

}

