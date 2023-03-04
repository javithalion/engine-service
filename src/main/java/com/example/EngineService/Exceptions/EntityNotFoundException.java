package com.example.EngineService.Exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String Message){
        super(Message);
    }
}
