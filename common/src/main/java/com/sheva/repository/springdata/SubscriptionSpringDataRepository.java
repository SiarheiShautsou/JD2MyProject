package com.sheva.repository.springdata;

import com.sheva.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface SubscriptionSpringDataRepository extends JpaRepository<Subscription, Long> {

    @Modifying
    @Query(value = "insert into training_place.subscription(user_id, gym_id) values (:user_id, :gym_id)", nativeQuery = true)
    int addUserIdAndGymId(@Param("user_id") Long userId, @Param("gym_id") Integer gymId);
}
