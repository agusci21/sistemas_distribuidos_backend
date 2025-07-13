package com.example.auth0UsersAndRoles.entities.dto;

import lombok.Data;

import java.util.List;
@Data
public class AssingRoleDTO {
    private String id;
    private List<String> roles;
}
