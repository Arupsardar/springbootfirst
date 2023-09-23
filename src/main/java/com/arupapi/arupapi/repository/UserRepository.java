package com.arupapi.arupapi.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arupapi.arupapi.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsByEmail(String email);


    Optional<User> findByEmail(String email);
    
}
