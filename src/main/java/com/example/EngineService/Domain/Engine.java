package com.example.EngineService.Domain;

import java.time.Instant;

public class Engine {

    private String serialNumber;

    private EngineType type;

    private String details;

    private Instant creationDateTime;


    public Engine(String serialNumber, EngineType type) {

        if(serialNumber == null || serialNumber.trim().isEmpty())
            throw new IllegalArgumentException("Engine serial number was null or empty or a set of blank spaces. Please provide valid serial number.");

        if(serialNumber.startsWith("I-"))
            throw new IllegalArgumentException("Engine serial number can't start with 'I-'. Please provide valid serial number.");

        if(type == null)
            throw new IllegalArgumentException("Engine Type was null. Please provide valid engine type.");

        this.serialNumber = serialNumber;
        this.type = type;
        this.details = "";
        this.creationDateTime = Instant.now();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public EngineType getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Instant creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "serialNumber='" + serialNumber + '\'' +
                ", type=" + type.getCode() +
                ", details='" + details + '\'' +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
