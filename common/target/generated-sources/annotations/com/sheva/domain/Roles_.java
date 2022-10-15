package com.sheva.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Roles.class)
public abstract class Roles_ {

	public static volatile SingularAttribute<Roles, Timestamp> modificationDate;
	public static volatile SingularAttribute<Roles, SystemRoles> roleName;
	public static volatile SingularAttribute<Roles, Long> id;
	public static volatile SingularAttribute<Roles, Timestamp> creationDate;
	public static volatile SetAttribute<Roles, User> users;

	public static final String MODIFICATION_DATE = "modificationDate";
	public static final String ROLE_NAME = "roleName";
	public static final String ID = "id";
	public static final String CREATION_DATE = "creationDate";
	public static final String USERS = "users";

}

