package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Qualifier("passwordEncoder")
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){

		return args ->{
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_MANAGER_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_MANAGER_SUPER_ADMIN"));

			userService.saveUser(new User(null,"nazir","nazir@gmail.com","123",new ArrayList<>()));
			userService.saveUser(new User(null,"asfas","asfas@gmail.com","543",new ArrayList<>()));
			userService.saveUser(new User(null,"mdmdd","mdmdd@gmail.com","876",new ArrayList<>()));
			userService.saveUser(new User(null,"okooi","okooi@gmail.com","098",new ArrayList<>()));

			userService.addRoleToUser("nazir@gmail.com","ROLE_ADMIN");
			userService.addRoleToUser("asfas@gmail.com","ROLE_USER");
			userService.addRoleToUser("mdmdd@gmail.com","ROLE_USER");
			userService.addRoleToUser("okooi@gmail.com","ROLE_USER");
			userService.addRoleToUser("nazir@gmail.com","ROLE_MANAGER_ADMIN");
			userService.addRoleToUser("nazir@gmail.com","ROLE_MANAGER_SUPER_ADMIN");
		};
	}
}
