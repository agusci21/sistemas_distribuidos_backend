package com.example.auth0UsersAndRoles.entities.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String lastName;
    private String nickName;
}