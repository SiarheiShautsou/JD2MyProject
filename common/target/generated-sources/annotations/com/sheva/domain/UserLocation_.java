package com.sheva.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserLocation.class)
public abstract class UserLocation_ {

	public static volatile SingularAttribute<UserLocation, String> country;
	public static volatile SingularAttribute<UserLocation, String> city;
	public static volatile SingularAttribute<UserLocation, Long> latitude;
	public static volatile SingularAttribute<UserLocation, Long> longitude;

	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";

}

