package com.arupapi.arupapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arupapi.arupapi.entities.Book;
import com.arupapi.arupapi.repository.BookRepository;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookContriller {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/creatbook")
    public String creatBook(@RequestBody Book book){
        bookRepository.save(book);
        return ""+book;
    }

    @GetMapping("/fetchallbook")
    public ResponseEntity<List<Book>> getAllBook(){
        List <Book> booklist =new ArrayList<>();
        bookRepository.findAll().forEach(booklist::add);
        return new ResponseEntity<List<Book>>(booklist,HttpStatus.OK);
    }
    
}
