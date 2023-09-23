package com.arupapi.arupapi.controller;

import java.lang.module.ResolutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arupapi.arupapi.config.JwtTokenHelper;
import com.arupapi.arupapi.entities.User;
import com.arupapi.arupapi.playload.JwtAuthResponse;
import com.arupapi.arupapi.playload.Loginbody;
import com.arupapi.arupapi.services.userService;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
   
@Autowired
   private userService userservice;

   @Autowired
   private JwtTokenHelper jwtTokenHelper;

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private AuthenticationManager authenticationManager;


    @PostMapping("/singin")
    public String creatUser(@RequestBody User user){
      return this.userservice.creatUser(user); 
    }
    
    @PostMapping("/logini")
    public String userlogin(@RequestBody Loginbody loginbody){
       return "login" +loginbody;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> creatLogin(@RequestBody Loginbody loginbody) throws Exception{
        
        this.authticate(loginbody.getUsername(),loginbody.getPassword());
        
        UserDetails userDetails =this.userDetailsService.loadUserByUsername(loginbody.getUsername());
        String token =this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response =new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);

    }

    private void authticate(String username, String password) throws Exception {
      UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(username, password);
      try {
        this.authenticationManager.authenticate(authenticationToken);
      } catch (ResolutionException e) {
        System.out.println("invalid detalis");
        throw new Exception("invalid Detalis");
      }
      
    }

   
    
    

}
