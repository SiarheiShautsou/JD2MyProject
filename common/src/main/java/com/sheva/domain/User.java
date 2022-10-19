package com.sheva.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"roles", "bodyParameters", "clientTrainings", "trainerTrainings",
"subscriptions", "trainerGym"})
@ToString(exclude = {"roles", "bodyParameters", "clientTrainings", "trainerTrainings",
        "subscriptions", "trainerGym"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
    private String userSurname;

    @Column
    private Timestamp birth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "country")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "latitude", column = @Column(name = "user_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "user_longitude"))
    })
    private UserLocation location;

    @Embedded
    private UserCredentials userCredentials;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender =Gender.NOT_SELECTED;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnoreProperties("users")
    private Set<Roles> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonManagedReference
    private Set<BodyParameters> bodyParameters;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonManagedReference
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonManagedReference
    private Set<Training> clientTrainings;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonManagedReference
    private Set<Training> trainerTrainings;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "l_gyms_trainers",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "gym_id"))
    @JsonBackReference
    private Gym trainerGym;

}
