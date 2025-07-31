package com.example.auth0UsersAndRoles.controlers;

import com.example.auth0UsersAndRoles.services.StarWarsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/starwars")
public class StarWarsController {

    private final StarWarsService starWarsService;

    public StarWarsController(StarWarsService starWarsService) {
        this.starWarsService = starWarsService;
    }

    @Operation(summary = "Get all star wars characters", description = "Fetches characters from http://localhost:3000/personajes")
    @GetMapping("/personajes")
    public ResponseEntity<String> getCharacters() {
        try {
            String response = starWarsService.getCharacters();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching characters: " + e.getMessage());
        }
    }
}