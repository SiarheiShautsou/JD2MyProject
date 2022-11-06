package com.sheva.repository;

import com.sheva.domain.Gym;
import com.sheva.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserSpringDataRepository extends JpaRepository<User, Long> {

    String findCredentialsUserLoginById(Long userid);

    String findCredentialsUserPasswordById(Long userid);

    @Query(value = "select u from User u")
    List<User> findByHQLQuery();

    Optional<User> findByUserCredentialsUserLogin(String login);

    Optional<User> findUserByUserNameAndUserSurname(String name, String surname);

//    @Query(value = "select u from User u inner join UserRole r where r.roleName like ")
    List<User> findAllByLocationCity(String city);

    List<User> findAllByTrainerGym(Gym gym);


    @Modifying
    @Query(value = "insert into training_place.l_role_user(role_id, user_id, creation_date, modification_date) values (:role_id, :user_id, :creation_date, :modification_date)", nativeQuery = true)
    int createRoleRow(@Param("role_id") Long roleId, @Param("user_id") Long userId, @Param("creation_date") Timestamp creationDate, @Param("modification_date") Timestamp modificationDate);

    @Modifying
    @Query(value = "insert into training_place.l_gyms_trainers(trainer_id, gym_id, creation_date, modification_date) values (:trainer_id, :gym_id, :creation_date, :modification_date)", nativeQuery = true)
    int createGymTrainerRow(@Param("trainer_id") Long trainerId, @Param("gym_id") Integer gymId, @Param("creation_date") Timestamp creationDate, @Param("modification_date") Timestamp modificationDate);
}



