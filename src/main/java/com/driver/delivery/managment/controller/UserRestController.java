package com.driver.delivery.managment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.driver.delivery.managment.dto.LoginDto;
import com.driver.delivery.managment.dto.RegisterDto;
import com.driver.delivery.managment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserRestController {

	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
		return userService.register(registerDto);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
		return userService.authenticate(loginDto);
	}

}
