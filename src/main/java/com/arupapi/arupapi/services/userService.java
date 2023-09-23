package com.arupapi.arupapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arupapi.arupapi.entities.User;
import com.arupapi.arupapi.repository.UserRepository;

@Service
public class userService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    public String creatUser(User user){
        String email =user.getEmail();
        if(userRepository.existsByEmail(email)){
         return "user already exit";   
        }else{
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return "user cerat sucessfull";
        }
    }

    
}
