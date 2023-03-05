package com.example.EngineService.Domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                .isThrownBy(() -> new Engine(invalidSerialNumber, engineType))
                .withMessageEndingWith("Engine serial number was null or empty or a set of blank spaces. Please provide valid serial number.");
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenProvidingNullEngineType(){
        //Arrange
        String serialNumber = "123456789";

        //Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Engine(serialNumber, null))
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
        String expectedUnitTypeCode = "ABC-123";
        String expectedToStringForInstant = "2022-02-15T18:35:24.00Z";
        EngineType myEngineType = mock(EngineType.class); // 1) Mocking our EngineType class
        Engine engine = new Engine(serialNumber, myEngineType); // 2) Injecting engine type mock via constructor
        Instant myCreationDateAndTime = mock(Instant.class); // 3) Mocking Instant class. Special class (final) see https://www.baeldung.com/mockito-final
        engine.setCreationDateTime(myCreationDateAndTime); // 4) Injecting Instant mock via method
        when(myEngineType.getCode()).thenReturn(expectedUnitTypeCode); // 5) Configuring EngineType mock
        when(myCreationDateAndTime.toString()).thenReturn(expectedToStringForInstant); // 6) Configuring Instant mock
        String expectedEngineToString =
                "Engine{" +
                        "serialNumber='123456789'" +
                        ", type=ABC-123" +
                        ", details=''" +
                        ", creationDateTime=2022-02-15T18:35:24.00Z" +
                        '}';

        //Act
        String toString = engine.toString();

        //Assert
        assertThat(toString).isEqualTo(expectedEngineToString);
    }
}