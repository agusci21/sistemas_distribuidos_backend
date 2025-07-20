package com.example.auth0UsersAndRoles.controlers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Public", description = "Operaciones públicas accesibles sin autenticación")
@RestController
@RequestMapping(path = "/api/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {

    @Operation(summary = "Endpoint público", description = "Devuelve un mensaje público accesible sin autenticación.")
    @GetMapping(value = "")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body(
                "{ \"message\": \"Este es un endpoint publico, podes ver esta respuesta sin estar autenticado.\"}");
    }

    @Operation(summary = "Endpoint Yedi", description = "Devuelve un mensaje para usuarios Yedi.")
    @GetMapping(value = "/api/yedi")
    public ResponseEntity<?> yediEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body(
                "{ \"message\": \"Este es un endpoint de Yedi.\"}");
    }

    @Operation(summary = "Endpoint Sith", description = "Devuelve un mensaje para usuarios Sith.")
    @GetMapping(value = "/api/sith")
    public ResponseEntity<?> sithEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body(
                "{ \"message\": \"Este es un endpoint de Sith.\"}");
    }

    @Operation(summary = "Endpoint Civilian", description = "Devuelve un mensaje para usuarios Civilian.")
    @GetMapping(value = "/api/civilian")
    public ResponseEntity<?> civilianEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body(
                "{ \"message\": \"Este es un endpoint de Civilian.\"}");
    }
}