package com.example.auth0UsersAndRoles.services;

import com.example.auth0UsersAndRoles.entities.User;
import com.example.auth0UsersAndRoles.entities.UserFavoritePokemon;
import com.example.auth0UsersAndRoles.repositories.UserFavoritePokemonRepository;
import com.example.auth0UsersAndRoles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    // Cambiar el tipo de userId a String para Auth0
    private final UserFavoritePokemonRepository userFavoritePokemonRepository;
    private final UserRepository userRepository;

    public PokemonService(RestTemplate restTemplate, UserFavoritePokemonRepository userFavoritePokemonRepository,
            UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userFavoritePokemonRepository = userFavoritePokemonRepository;
        this.userRepository = userRepository;
    }

    public String getPokemonsFromApi() {
        String url = "https://pokeapi.co/api/v2/pokemon";
        return restTemplate.getForObject(url, String.class);
    }

    public boolean addPokemonAsFavorite(String userAuth0Id, Integer pokemonId) {
        User user = userRepository.getUserByAuth0Id(userAuth0Id);
        if (user == null)
            return false;
        Long userId = user.getId();
        boolean exists = userFavoritePokemonRepository.existsByUserIdAndPokemonId(userId, pokemonId);
        if (!exists) {
            UserFavoritePokemon favorite = new UserFavoritePokemon();
            favorite.setUserId(userId);
            favorite.setPokemonId(pokemonId);
            userFavoritePokemonRepository.save(favorite);
            return true;
        }
        return false;
    }

    public List<Integer> getFavoritePokemonsForUser(String userAuth0Id) {
        User user = userRepository.getUserByAuth0Id(userAuth0Id);
        if (user == null)
            return Collections.emptyList();
        Long userId = user.getId();
        return userFavoritePokemonRepository.findByUserId(userId)
                .stream()
                .map(UserFavoritePokemon::getPokemonId)
                .toList();
    }
}