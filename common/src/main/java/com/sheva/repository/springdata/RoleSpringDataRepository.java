package com.sheva.repository.springdata;

import com.sheva.domain.Roles;
import com.sheva.domain.SystemRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleSpringDataRepository extends JpaRepository<Roles, Long> {

    List<Roles> findByUsersId(Long userId);

    Roles findByRoleName(SystemRoles roleName);
}

