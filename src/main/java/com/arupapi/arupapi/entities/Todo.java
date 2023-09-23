package com.arupapi.arupapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    private  Long userId;

    private boolean is_complite;

    

    public Todo() {
    }


    public Todo(String name, Long userId, boolean is_complite) {
        this.name = name;
        this.userId = userId;
        this.is_complite = is_complite;
    }






    public Todo(Long id, String name, Long userId, boolean is_complite) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.is_complite = is_complite;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_complite() {
        return is_complite;
    }

    public void setIs_complite(boolean is_complite) {
        this.is_complite = is_complite;
    }



    public Long getUserId() {
        return userId;
    }



    public void setUserId(Long userId) {
        this.userId = userId;
    }

    


}
