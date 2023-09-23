package com.arupapi.arupapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;

    private String title;
    
    public Book(int id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

   

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", title=" + title + "]";
    }

    public Book() {
    }


    
    
}
