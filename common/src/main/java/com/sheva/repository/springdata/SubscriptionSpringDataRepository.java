package com.sheva.repository.springdata;

import com.sheva.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionSpringDataRepository extends JpaRepository<Subscription, Long> {
}
