package com.arupapi.arupapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.arupapi.arupapi.entities.Todo;
import com.arupapi.arupapi.entities.User;
import com.arupapi.arupapi.playload.Todopayload;
import com.arupapi.arupapi.repository.TodoRepository;
import com.arupapi.arupapi.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/todo")
public class TodoController {

   
@Autowired
   TodoRepository todoRepository;

   @PostMapping("/creattodo")
    public String creatBook(@RequestBody Todopayload todopayload){
        Todo newtodo =new Todo( todopayload.getName(), this.getuserid(), false);
        
        todoRepository.save(newtodo);
        return ""+todopayload;
    }

    @PostMapping("/creattodo1")
    public ResponseEntity<String> creatbulkTodo(@RequestBody List<Todo> todos){
        todoRepository.saveAll(todos);
        return ResponseEntity.ok("data uplode succfully");
    }


   @GetMapping("/fetchalltodo")
    public ResponseEntity<List<Todo>> getAllBook(){
        List<Todo>  todolist= todoRepository.findByUserId(this.getuserid());
        return new ResponseEntity<List<Todo>>(todolist,HttpStatus.OK);
    } 

    @GetMapping("/fatchsingle/{todoid}")
    public ResponseEntity<Todo> getTodoByID(@PathVariable Long todoid){
        Optional<Todo> todo= todoRepository.findById(todoid);
        if(todo.isPresent()){
            return new ResponseEntity<Todo>(todo.get(),HttpStatus.FOUND);
        }else{
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/fatchsingle/{todoid}")
    public ResponseEntity<Todo> setTotobyId(@PathVariable Long todoid, @RequestBody Todopayload todo){
       Optional<Todo> oldtodo=  todoRepository.findById(todoid);
      if(oldtodo.isPresent()){
        Todo exittodo =oldtodo.get();
        exittodo.setName(todo.getName());
        exittodo.setUserId(this.getuserid());
        exittodo.setIs_complite(todo.isComplite());
        todoRepository.save(exittodo);
        return new ResponseEntity<Todo>(exittodo,HttpStatus.UPGRADE_REQUIRED);
      }else{
        return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/fatchsingle/{todoid}")
    public String getDeletTodo(@PathVariable Long todoid){
        todoRepository.deleteById(todoid);
        return "to do list delete sucessfully";
    }
    
    @GetMapping("/getuser")
    public String yourMethod() {
        UserDetails userDetails;
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Access the authenticated user details
            userDetails = (UserDetails) authentication.getPrincipal();
            return ""+userDetails.getUsername();
        }
        return null;
    }


    @Autowired
    UserRepository userRepository;

    private Long getuserid(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
           
           UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
           Optional<User> user =userRepository.findByEmail(userDetails.getUsername());
           User loguser =user.get();
           return loguser.getId();
        }
        return null;
    }
    
    
}
