package com.driver.delivery.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.driver.delivery.managment.entity.Role;
import com.driver.delivery.managment.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(RoleName roleName);

}
