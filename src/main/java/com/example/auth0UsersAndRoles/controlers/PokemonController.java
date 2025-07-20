package com.example.auth0UsersAndRoles.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final RestTemplate restTemplate;

    public PokemonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Get all pokemons", description = "Fetches pokemons from PokeAPI")
    @GetMapping("/get-all-pokemons")
    public ResponseEntity<String> getPokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon";
        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error fetching pokemons: " + e.getMessage());
        }
    }
}