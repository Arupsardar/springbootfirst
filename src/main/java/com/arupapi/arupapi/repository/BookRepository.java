package com.arupapi.arupapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.arupapi.arupapi.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
    
}
