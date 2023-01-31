package com.example.EngineService.Domain;

import java.time.Instant;

public class EngineType {

    private String code;

    private String description;

    private Instant creationDateTime;


    public EngineType(String code){

        if(code == null || code.trim().isEmpty())
            throw new IllegalArgumentException("Engine Type code was null or empty or a set of blank spaces. Please provide valid code.");

        this.code = code;
        this.description = String.format("Engine type with code %s", code);
        this.creationDateTime = Instant.now();
    }


    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

}
