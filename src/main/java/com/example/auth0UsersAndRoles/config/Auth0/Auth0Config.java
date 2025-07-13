package com.example.auth0UsersAndRoles.config.Auth0;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.json.auth.TokenHolder;
import com.auth0.client.auth.AuthAPI;
import com.auth0.net.AuthRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class Auth0Config {

    private static final Logger logger = LoggerFactory.getLogger(Auth0Config.class);

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Bean
    public ManagementAPI managementAPI() throws Exception {
        try {
            logger.info("Iniciando configuración del Management API de Auth0...");
            logger.info("Domain: {}", domain);
            logger.info("Client ID: {}", clientId);
            
            AuthAPI authAPI = new AuthAPI(domain, clientId, clientSecret);
            AuthRequest request = authAPI.requestToken("https://" + domain + "/api/v2/");
            
            logger.info("Solicitando token de acceso para Management API...");
            TokenHolder holder = request.execute();
            
            logger.info("Token obtenido exitosamente");
            ManagementAPI managementAPI = new ManagementAPI(domain, holder.getAccessToken());
            
            logger.info("Management API configurado correctamente");
            return managementAPI;
            
        } catch (Exception e) {
            logger.error("Error al configurar Management API: {}", e.getMessage(), e);
            throw new RuntimeException("No se pudo configurar el Management API de Auth0. " +
                "Verifica las credenciales y permisos de la aplicación Machine to Machine.", e);
        }
    }
}

