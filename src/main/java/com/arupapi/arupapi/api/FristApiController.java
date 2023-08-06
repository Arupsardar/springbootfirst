package com.arupapi.arupapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FristApiController {

   @GetMapping("/hello")
    public String  sayHello(){
        return "Hello World";
    }
}
