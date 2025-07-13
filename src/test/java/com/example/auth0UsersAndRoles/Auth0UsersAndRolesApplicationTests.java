package com.example.auth0UsersAndRoles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.autoconfigure.exclude=com.example.auth0UsersAndRoles.config.Auth0.Auth0Config"
})
class Auth0UsersAndRolesApplicationTests {

	@Test
	void contextLoads() {
	}

}
