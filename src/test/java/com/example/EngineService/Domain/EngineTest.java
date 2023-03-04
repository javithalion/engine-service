package com.example.EngineService.Domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class EngineTest {

    @Test
    void constructor_shouldCreatedExpectedInstance_whenProvidingValidSerialNumberAndEngineType(){
        //Arrange
        String serialNumber = "123456789";
        EngineType type = new EngineType("ABC-123");

        //Act
        Engine engine = new Engine(serialNumber, type);

        //Assert
        assertThat(engine.getSerialNumber()).isEqualTo(serialNumber);
        assertThat(engine.getType()).isEqualTo(type);
        assertThat(engine.getCreationDateTime()).isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void constructor_shouldThrowIllegalArgumentException_whenProvidingInvalidSerialNumber(String invalidSerialNumber){
        //Arrange
        EngineType engineType = new EngineType("ABC-123");

        //Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {new Engine(invalidSerialNumber, engineType);})
                .withMessageEndingWith("Engine serial number was null or empty or a set of blank spaces. Please provide valid serial number.");
    }

    @ParameterizedTest
    @NullSource
    void constructor_shouldThrowIllegalArgumentException_whenProvidingNullEngineType(EngineType invalidEngineType){
        //Arrange
        String serialNumber = "123456789";

        //Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {new Engine(serialNumber, invalidEngineType);})
                .withMessageEndingWith("Engine Type was null. Please provide valid engine type.");
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenProvidingSerialNumberStartingWithI(){
        //Arrange
        String serialNumberWithI = "I-123456789";
        EngineType engineType= new EngineType("ABC-123");

        //Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Engine(serialNumberWithI, engineType))
                .withMessageEndingWith("Engine serial number can't start with 'I-'. Please provide valid serial number.");

    }

    @Test
    void setDetails_shouldModifyEngineDetails_whenProvidingValidValue(){
        //Arrange
        String serialNumber = "123456789";
        EngineType engineType = new EngineType("ABC-123");
        Engine engine = new Engine(serialNumber, engineType);

        String newDetails = String.format("New details for engine %s", serialNumber);

        //Act
        engine.setDetails(newDetails);

        //Assert
        assertThat(engine.getDetails()).isEqualTo(newDetails);
    }

    @Test
    void setCreationDateTime_shouldModifyEngineCreationDateTime_whenProvidingValidValue(){
        //Arrange
        String serialNumber = "123456789";
        EngineType engineType = new EngineType("ABC-123");
        Engine engine = new Engine(serialNumber, engineType);

        Instant newCreationDateTime = Instant.now().plusSeconds(60);


        //Act
        engine.setCreationDateTime(newCreationDateTime);

        //Assert
        assertThat(engine.getCreationDateTime()).isEqualTo(newCreationDateTime);
    }

    @Test
    void toString_shouldShowEngineDataToString_whenProvidingValidValues(){
        //Arrange
        String serialNumber = "123456789";
        EngineType engineType = new EngineType("ABC-123");
        Engine engine = new Engine(serialNumber, engineType);

        //Act
        String toString = engine.toString();

        //Assert
        String engineToString =
                "Engine{" +
                        "serialNumber='" + serialNumber + '\'' +
                        ", type=" + engineType.getCode() +
                        ", details='" + engine.getDetails() + '\'' +
                        ", creationDateTime=" + engine.getCreationDateTime() +
                        '}';
        assertThat(toString).isEqualTo(engineToString);

    }

}