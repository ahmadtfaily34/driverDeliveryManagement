package com.driver.delivery.managment.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.driver.delivery.managment.dto.LoginDto;
import com.driver.delivery.managment.dto.RegisterDto;
import com.driver.delivery.managment.entity.Role;
import com.driver.delivery.managment.entity.RoleName;
import com.driver.delivery.managment.entity.User;
import com.driver.delivery.managment.repository.RoleRepository;
import com.driver.delivery.managment.repository.UserRepository;
import com.driver.delivery.managment.security.JwtUtilities;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtilities jwtUtilities;

	UserService(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtilities jwtUtilities) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtilities = jwtUtilities;
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public ResponseEntity<?> register(RegisterDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			return new ResponseEntity<>("username is already taken !", HttpStatus.SEE_OTHER);
		} else {
			User user = new User();
			user.setUsername(registerDto.getUsername());
			user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
			Role role = roleRepository.findByRoleName(RoleName.valueOf(registerDto.getRole()));
			user.setRoles(Collections.singletonList(role));
			userRepository.save(user);
			String token = jwtUtilities.generateToken(registerDto.getUsername(),
					Collections.singletonList(role.getRoleName()));
			return new ResponseEntity<>(token, HttpStatus.OK);

		}
	}

	@Override
	public ResponseEntity<String> authenticate(LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		List<String> rolesNames = new ArrayList<>();
		user.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));
		String token = jwtUtilities.generateToken(user.getUsername(), rolesNames);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

}
