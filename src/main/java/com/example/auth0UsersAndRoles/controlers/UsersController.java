package com.example.auth0UsersAndRoles.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth0UsersAndRoles.entities.User;
import com.example.auth0UsersAndRoles.entities.dto.UserUpdateDTO;
import com.example.auth0UsersAndRoles.services.UserBBDDService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@Tag(name = "Users", description = "Operaciones para gestión de usuarios")
@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    @Autowired
    private UserBBDDService service;

    // @GetMapping("")
    // public ResponseEntity<Map<String, Object>> getUsers(@AuthenticationPrincipal
    // Jwt jwt) {

    // String jwtClaims = jwt.getClaims().toString();

    // return ResponseEntity.ok(jwtClaims);
    // }

    @GetMapping("/decode-jwt")
    public ResponseEntity<User> decodeJwt(@AuthenticationPrincipal Jwt jwt) throws Exception {
        String auth0Id = jwt.getSubject();
        User user = this.service.findById(auth0Id);

        if (user == null) {
            user = new User();
            user.setAuth0Id(auth0Id);
            user.setUserEmail(jwt.getClaimAsString("email"));
            user.setName(jwt.getClaimAsString("name"));
            user.setNickName(jwt.getClaimAsString("nickname"));
            user = this.service.save(user);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@AuthenticationPrincipal Jwt jwt) throws Exception {
        String auth0Id = jwt.getSubject();
        User existingUser = this.service.findById(auth0Id);

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El usuario con auth0Id '" + auth0Id + "' ya existe.");
        }

        User newUser = new User();
        newUser.setAuth0Id(auth0Id);
        newUser.setUserEmail(jwt.getClaimAsString("email"));
        newUser.setName(jwt.getClaimAsString("name"));
        newUser.setNickName(jwt.getClaimAsString("nickname"));

        User savedUser = this.service.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal Jwt jwt, @RequestBody UserUpdateDTO userUpdateDTO)
            throws Exception {
        String auth0Id = jwt.getSubject();
        User userToUpdate = this.service.findById(auth0Id);

        if (userToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no fue encontrado.");
        }

        userToUpdate.setName(userUpdateDTO.getName());
        userToUpdate.setLastName(userUpdateDTO.getLastName());
        userToUpdate.setNickName(userUpdateDTO.getNickName());

        User updatedUser = this.service.update(userToUpdate);

        return ResponseEntity.ok(updatedUser);
    }
}