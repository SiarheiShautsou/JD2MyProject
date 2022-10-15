package com.sheva.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserCredentials.class)
public abstract class UserCredentials_ {

	public static volatile SingularAttribute<UserCredentials, String> userLogin;
	public static volatile SingularAttribute<UserCredentials, String> userPassword;
	public static volatile SingularAttribute<UserCredentials, String> mobileNumber;
	public static volatile SingularAttribute<UserCredentials, String> userEmail;

	public static final String USER_LOGIN = "userLogin";
	public static final String USER_PASSWORD = "userPassword";
	public static final String MOBILE_NUMBER = "mobileNumber";
	public static final String USER_EMAIL = "userEmail";

}

