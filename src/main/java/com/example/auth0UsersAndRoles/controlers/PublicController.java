package com.example.auth0UsersAndRoles.controlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth0UsersAndRoles.entities.Roles;
import com.example.auth0UsersAndRoles.entities.User;
import com.example.auth0UsersAndRoles.entities.dto.UserDTO;
import com.example.auth0UsersAndRoles.repositories.RoleRepository;
import com.example.auth0UsersAndRoles.services.UserBBDDService;
import com.example.auth0UsersAndRoles.services.UserAuth0Service;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "Public", description = "Operaciones públicas accesibles sin autenticación")
@RestController
@RequestMapping(path = "/api/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {

    private final UserAuth0Service userAuth0Service;
    private final UserBBDDService userBBDDService;
    private final RoleRepository roleRepository;

    public PublicController(UserAuth0Service userAuth0Service, UserBBDDService userBBDDService,
            RoleRepository roleRepository) {
        this.userAuth0Service = userAuth0Service;
        this.userBBDDService = userBBDDService;
        this.roleRepository = roleRepository;
    }

    @Operation(summary = "Endpoint público", description = "Devuelve un mensaje público accesible sin autenticación.")
    @GetMapping(value = "")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body(
                "{ \"message\": \"Este es un endpoint publico, podes ver esta respuesta sin estar autenticado.\"}");
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un usuario en Auth0 y en la base de datos local con el rol de Cliente.")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            // Asignamos la conexión por defecto
            userDTO.setConnection("Username-Password-Authentication");

            // ID del rol de Cliente en Auth0. ¡IMPORTANTE! Reemplazar con tu ID de rol.
            String clienteRoleId = "rol_K96JEx5iqNavmbOt";
            userDTO.setRoles(Collections.singletonList(clienteRoleId));

            // 1. Crear el usuario en Auth0
            com.auth0.json.mgmt.users.User newUserAuth0 = userAuth0Service.createUser(userDTO);

            // 2. Asignar el rol en Auth0
            userAuth0Service.assignRoles(newUserAuth0.getId(), userDTO.getRoles());

            // 3. Buscar el rol de Cliente en nuestra BBDD
            Roles rolCliente = roleRepository.findByAuth0RoleId(clienteRoleId)
                    .orElseThrow(() -> new RuntimeException("El rol 'Cliente' no se encuentra en la base de datos."));

            // 4. Crear el usuario en nuestra BBDD
            User userBBDD = User.builder()
                    .auth0Id(newUserAuth0.getId())
                    .userEmail(newUserAuth0.getEmail())
                    .name(newUserAuth0.getName())
                    .nickName(userDTO.getNickName())
                    .roles(Collections.singleton(rolCliente))
                    .build();

            userBBDDService.save(userBBDD);

            return ResponseEntity.status(HttpStatus.CREATED).body(userBBDD);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar el usuario: " + e.getMessage());
        }
    }
}