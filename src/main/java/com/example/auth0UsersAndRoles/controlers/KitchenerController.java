package com.example.auth0UsersAndRoles.controlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Kitchener", description = "Operaciones para usuarios cocinero autenticados")
@RestController
@RequestMapping(path = "/api/kitchener", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenerController {

    @Operation(summary = "Endpoint de cocinero", description = "Devuelve un mensaje para usuarios autenticados como cocinero.")
    @GetMapping(value = "")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("{ \"message\": \"Este es un endpoint de Cocinero.\"}");
    }

}