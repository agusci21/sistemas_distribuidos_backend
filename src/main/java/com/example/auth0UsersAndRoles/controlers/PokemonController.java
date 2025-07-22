package com.example.auth0UsersAndRoles.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import com.example.auth0UsersAndRoles.services.PokemonService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Operation(summary = "Get all pokemons", description = "Fetches pokemons from PokeAPI")
    @GetMapping("/get-all-pokemons")
    public ResponseEntity<String> getPokemons() {
        try {
            String response = pokemonService.getPokemonsFromApi();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching pokemons: " + e.getMessage());
        }
    }

    @Operation(summary = "Agregar pokemon como favorito", description = "Agrega un pokemon a la lista de favoritos de un usuario")
    @PostMapping("/add-pokemon-as-favorite/{pokemonId}")
    public ResponseEntity<String> addPokemonAsFavorite(@PathVariable Integer pokemonId,
            @AuthenticationPrincipal Jwt jwt) {
        String userAuth0Id = jwt.getSubject();
        // Aquí deberías buscar el id interno del usuario en la base de datos usando el
        // auth0Id
        // Por ahora, solo mostramos el auth0Id
        // TODO: Mapear auth0Id a id interno si es necesario
        boolean added = pokemonService.addPokemonAsFavorite(userAuth0Id, pokemonId);
        if (added) {
            return ResponseEntity.ok("Pokemon agregado a favoritos");
        } else {
            return ResponseEntity.badRequest().body("El pokemon ya está en favoritos");
        }
    }
}