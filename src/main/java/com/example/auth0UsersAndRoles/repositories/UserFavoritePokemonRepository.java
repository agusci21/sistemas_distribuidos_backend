package com.example.auth0UsersAndRoles.repositories;

import com.example.auth0UsersAndRoles.entities.UserFavoritePokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoritePokemonRepository extends JpaRepository<UserFavoritePokemon, Long> {
    List<UserFavoritePokemon> findByUserId(Long userId);

    boolean existsByUserIdAndPokemonId(Long userId, Integer pokemonId);
}