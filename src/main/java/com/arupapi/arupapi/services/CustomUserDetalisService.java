package com.arupapi.arupapi.services;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arupapi.arupapi.entities.User;
import com.arupapi.arupapi.repository.UserRepository;

@Service
public class CustomUserDetalisService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
      User user= this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email :"+username ,100));
        
        return user;
    }

    
    
}
