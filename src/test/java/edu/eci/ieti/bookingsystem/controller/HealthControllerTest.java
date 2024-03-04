package edu.eci.ieti.bookingsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.ieti.bookingsystem.controller.health.HealthController;

@ExtendWith(MockitoExtension.class)
class HealthControllerTest {

    @Mock
    HealthController healthController;

    @Test
    void testCheckAPI() {
        // Arrange
        String response = "<h1>The API is working ok!</h1>";
        when(healthController.checkAPI()).thenReturn(response);

        // Act
        String actualResponse = healthController.checkAPI();

        // Assert
        assertEquals(response, actualResponse);
    }

}