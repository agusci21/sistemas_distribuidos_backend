package com.example.auth0UsersAndRoles.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    // Cambiar el tipo de userId a String para Auth0
    private final Map<String, List<Integer>> userFavorites = new HashMap<>();

    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPokemonsFromApi() {
        String url = "https://pokeapi.co/api/v2/pokemon";
        return restTemplate.getForObject(url, String.class);
    }

    public boolean addPokemonAsFavorite(String userAuth0Id, Integer pokemonId) {
        userFavorites.putIfAbsent(userAuth0Id, new ArrayList<>());
        List<Integer> favorites = userFavorites.get(userAuth0Id);
        if (!favorites.contains(pokemonId)) {
            favorites.add(pokemonId);
            return true;
        }
        return false;
    }

    public List<Integer> getFavorites(String userAuth0Id) {
        return userFavorites.getOrDefault(userAuth0Id, Collections.emptyList());
    }
}