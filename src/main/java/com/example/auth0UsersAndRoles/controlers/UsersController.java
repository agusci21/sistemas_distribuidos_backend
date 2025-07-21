package com.example.auth0UsersAndRoles.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth0UsersAndRoles.services.UserBBDDService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<String> decodeJwt(@AuthenticationPrincipal Jwt jwt) throws Exception {
        String jwtClaims = jwt.getSubject();
        System.out.println(jwtClaims);
        var user = this.service.findById(jwtClaims);
        System.err.println(user);
        return ResponseEntity.ok(jwtClaims); 
    }
}