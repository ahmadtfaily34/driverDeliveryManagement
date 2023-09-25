package com.driver.delivery.managment.services;

import org.springframework.http.ResponseEntity;

import com.driver.delivery.managment.dto.LoginDto;
import com.driver.delivery.managment.dto.RegisterDto;
import com.driver.delivery.managment.entity.Role;
import com.driver.delivery.managment.entity.User;

public interface IUserService {

	ResponseEntity<String> authenticate(LoginDto loginDto);

	ResponseEntity<?> register(RegisterDto registerDto);

	Role saveRole(Role role);

	User saveUser(User user);
}
