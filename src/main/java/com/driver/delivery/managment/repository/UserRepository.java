package com.driver.delivery.managment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.driver.delivery.managment.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);

}
