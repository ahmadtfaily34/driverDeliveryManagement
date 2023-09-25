package com.driver.delivery.managment.data;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.driver.delivery.managment.entity.Role;
import com.driver.delivery.managment.entity.RoleName;
import com.driver.delivery.managment.entity.User;
import com.driver.delivery.managment.repository.UserRepository;

@Component
public class AdminLoader implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AdminLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.getRoles().add(new Role(RoleName.ADMIN));

			User user = new User();
			user.setUsername("manager");
			user.setPassword(passwordEncoder.encode("manager"));
			user.getRoles().add(new Role(RoleName.MANAGER));

			userRepository.saveAll(Arrays.asList(admin, user));
		}
	}

}
