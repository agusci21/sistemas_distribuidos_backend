package com.example.auth0UsersAndRoles.repositories;

import com.example.auth0UsersAndRoles.entities.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends BaseRepository<User,Long> {

    User getUserByAuth0Id (String id);

}
