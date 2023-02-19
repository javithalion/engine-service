package com.example.EngineService.Domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class EngineTypeTest {

    @Test
    void constructor_shouldCreateExpectedInstance_whenProvidingValidEngineTypeCode(){
        // Arrange
        String originalCode = "ABC-123";
        String expectedDescription =  String.format("Engine type with code %s", originalCode);

        // Act
        EngineType engineType = new EngineType(originalCode);

        // Assert
        assertThat(engineType.getCode()).isEqualTo(originalCode);
        assertThat(engineType.getDescription()).isEqualTo(expectedDescription);
        assertThat(engineType.getCreationDateTime()).isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS));
    }

    @ParameterizedTest
    @NullAndEmptySource // adds null and empty values to the list of values to provide to the method
    @ValueSource(strings = { " " }) // adds a string with blank space to the list of values to provide to the method
    void constructor_shouldThrowIllegalArgumentException_whenProvidingInvalidEngineTypeCode(String invalidEngineTypeCode){
        // Arrange
        // N/A

        // Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> { new EngineType(invalidEngineTypeCode);})
                .withMessageEndingWith("Please provide valid code.");
    }

   @Test
    void constructor_shouldThrowIllegalArgumentException_whenProvidingEngineTypeCodeStartingWithC(){
        // Arrange
        String invalidEngineTypeCode = "C-This-is-a-test";

        // Act & Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> { new EngineType(invalidEngineTypeCode);})
                .withMessage("Engine type codes starting with C are considered test engine types and are not allowed.");
    }

    @Test
    void setDescription_shouldModifyEngineTypeDescription_whenProvidingValidValue() {
        // Arrange
        String originalCode = "ABC-123";
        EngineType engineType = new EngineType(originalCode);
        String newDescription = "AA_BB_CC";

        // Act
        engineType.setDescription(newDescription);

        // Assert
        assertThat(engineType.getDescription()).isEqualTo(newDescription);
    }

    @Test
    void setCreationDateTime_shouldModifyEngineTypeCreationDateTime_whenProvidingValidValue() {
        // Arrange
        String originalCode = "ABC-123";
        EngineType engineType = new EngineType(originalCode);
        Instant newCreationDateTime = Instant.now().plusSeconds(1234);

        // Act
        engineType.setCreationDateTime(newCreationDateTime);

        // Assert
        assertThat(engineType.getCreationDateTime()).isEqualTo(newCreationDateTime);
    }
}