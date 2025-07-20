package com.example.auth0UsersAndRoles.controlers;

import com.example.auth0UsersAndRoles.entities.Character;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "StarWars", description = "Operaciones de personajes de Star Wars")
@RestController
@RequestMapping("/api/starwars")
public class StarWarsController {

    @Operation(summary = "Obtener personajes de Star Wars", description = "Obtiene la lista de personajes desde la API pública de SWAPI")
    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getCharacters() {
        System.out.println("Endpoint /api/starwars/characters fue llamado");
        
        // Crear lista de personajes de prueba
        List<Character> characters = new ArrayList<>();
        
        Character luke = new Character();
        luke.setName("Luke Skywalker");
        luke.setHeight("172");
        luke.setMass("77");
        
        Character leia = new Character();
        leia.setName("Leia Organa");
        leia.setHeight("150");
        leia.setMass("49");
        
        characters.add(luke);
        characters.add(leia);
        
        return ResponseEntity.ok(characters);
    }
}