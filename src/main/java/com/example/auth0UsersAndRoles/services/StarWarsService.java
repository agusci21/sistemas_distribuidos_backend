package com.example.auth0UsersAndRoles.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsService {

    private final RestTemplate restTemplate;

    public StarWarsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCharacters() {
        String url = "http://localhost:3000/personajes";
        return restTemplate.getForObject(url, String.class);
    }
}