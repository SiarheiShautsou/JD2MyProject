package com.sheva.repository.springdata;

import com.sheva.domain.UserRole;
import com.sheva.domain.SystemRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleSpringDataRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUsersId(Long userId);

    UserRole findByRoleName(SystemRoles roleName);
}

