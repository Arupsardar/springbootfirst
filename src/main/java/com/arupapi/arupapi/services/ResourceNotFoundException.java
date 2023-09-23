package com.arupapi.arupapi.services;

public class ResourceNotFoundException extends RuntimeException {
    String ResoName;
    String fildname;
    int i;
    public ResourceNotFoundException(String ResoName, String fildname, int i) {
        this.ResoName =ResoName;
        this.fildname =fildname;
        this.i =i;
    }

}
