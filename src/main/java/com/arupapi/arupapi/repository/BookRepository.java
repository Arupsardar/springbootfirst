package com.arupapi.arupapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arupapi.arupapi.entities.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{
    
}
