package com.sheva.repository.springdata;

import com.sheva.domain.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
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

    @Modifying
    @Query(value = "insert into training_place.l_role_user(role_id, user_id, creation_date, modification_date) values (:role_id, :user_id, :creation_date, :modification_date)", nativeQuery = true)
    int createRoleRow(@Param("role_id") Long roleId, @Param("user_id") Long userId, @Param("creation_date") Timestamp creationDate, @Param("modification_date") Timestamp modificationDate);

}



