package com.example.auth0UsersAndRoles;

import com.example.auth0UsersAndRoles.entities.Roles;
import com.example.auth0UsersAndRoles.entities.User;
import com.example.auth0UsersAndRoles.entities.dto.RoleDTO;
import com.example.auth0UsersAndRoles.entities.dto.UserDTO;
import com.example.auth0UsersAndRoles.repositories.RoleRepository;
import com.example.auth0UsersAndRoles.services.RoleAuth0Service;
import com.example.auth0UsersAndRoles.services.RoleBBDDService;
import com.example.auth0UsersAndRoles.services.UserAuth0Service;
import com.example.auth0UsersAndRoles.services.UserBBDDService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.auth0.json.mgmt.Role;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Auth0UsersAndRolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Auth0UsersAndRolesApplication.class, args);
		System.out.println("Api corriendo");
	}

	/*
	 * @Bean
	 * public CommandLineRunner run(RoleAuth0Service roleService,
	 * RoleBBDDService roleServicebbdd,
	 * RoleRepository roleRepository,
	 * UserAuth0Service userService,
	 * UserBBDDService userBBDDService) {
	 * return args -> {
	 * 
	 * RoleDTO rolAdminDTO = new RoleDTO();
	 * rolAdminDTO.setName("Administrador");
	 * rolAdminDTO.setDescription("Admin del local");
	 * 
	 * RoleDTO rolClienteDTO = new RoleDTO();
	 * rolClienteDTO.setName("Cliente");
	 * rolClienteDTO.setDescription("Cliente del local");
	 * 
	 * // ==== 1. Crear Roles ====
	 * crearRolInicial(rolAdminDTO, roleService, roleServicebbdd);
	 * crearRolInicial(rolClienteDTO, roleService, roleServicebbdd);
	 * 
	 * // ==== 2. Crear Usuario Administrador ====
	 * Roles rolAdmin = roleServicebbdd.findByName("Administrador");
	 * 
	 * UserDTO adminDTO = new UserDTO();
	 * adminDTO.setEmail("admin@buensabor.com");
	 * adminDTO.setName("Administrador");
	 * adminDTO.setNickName("admin total");
	 * adminDTO.setPassword("Admin@admin");
	 * adminDTO.setConnection("Username-Password-Authentication");
	 * adminDTO.setRoles(List.of(rolAdmin.getAuth0RoleId()));
	 * 
	 * com.auth0.json.mgmt.users.User newUser = userService.createUser(adminDTO);
	 * userService.assignRoles(newUser.getId(), adminDTO.getRoles());
	 * 
	 * User adminBBDD = User.builder()
	 * .auth0Id(newUser.getId())
	 * .name(newUser.getName())
	 * .roles(Set.of(rolAdmin))
	 * .nickName(adminDTO.getNickName())
	 * .userEmail(newUser.getEmail())
	 * .build();
	 * 
	 * userBBDDService.save(adminBBDD);
	 * 
	 * System.out.println("Roles y usuario administrador creados correctamente.");
	 * 
	 * };
	 * 
	 * }
	 */

	private void crearRolInicial(RoleDTO roleDTO,
			RoleAuth0Service roleService,
			RoleBBDDService roleServicebbdd) throws Exception {
		com.auth0.json.mgmt.Role rolAuth = null;
		try {
			// Crear en Auth0
			rolAuth = roleService.createRole(roleDTO);

			// Guardar en BBDD
			Roles roles = Roles.builder()
					.auth0RoleId(rolAuth.getId())
					.description(roleDTO.getDescription())
					.name(roleDTO.getName())
					.build();

			roleServicebbdd.save(roles);

		} catch (Exception e) {
			// Revertir Auth0 si falla BBDD
			if (rolAuth != null && rolAuth.getId() != null) {
				try {
					roleService.deleteRole(rolAuth.getId());
				} catch (Exception ex) {
					System.err.println("Error al eliminar rol en Auth0: " + ex.getMessage());
				}
			}
			throw e;
		}
	}

}
