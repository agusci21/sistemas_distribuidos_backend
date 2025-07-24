package com.example.auth0UsersAndRoles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Auth0UsersAndRolesApplication {
	public static void main(String[] args) {
		SpringApplication.run(Auth0UsersAndRolesApplication.class, args);
	}
}
