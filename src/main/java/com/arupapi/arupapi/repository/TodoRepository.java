package com.arupapi.arupapi.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.arupapi.arupapi.entities.Todo;



public interface TodoRepository extends JpaRepository<Todo,Long> {


    List<Todo> findByUserId(Long userId);
    
}
