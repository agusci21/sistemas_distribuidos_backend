package com.example.auth0UsersAndRoles.repositories;

import com.example.auth0UsersAndRoles.entities.Roles;
import com.example.auth0UsersAndRoles.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Roles,Long> {
    Optional<Roles> findByAuth0RoleId(String name);
    Roles getRolesByAuth0RoleId (String id);
    Roles getRolesByName(String name);

}
