package edu.eci.ieti.bookingsystem.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.ieti.bookingsystem.controller.auth.AuthController;
import edu.eci.ieti.bookingsystem.controller.auth.LoginDto;
import edu.eci.ieti.bookingsystem.exception.InvalidCredentialsException;
import edu.eci.ieti.bookingsystem.security.jwt.JwtUtils;
import edu.eci.ieti.bookingsystem.service.user.UsersService;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    AuthController authController;
    @Mock
    UsersService usersService;
    @Mock
    JwtUtils jwtUtils;

    @Test
    void testLoginWithNonexistentUser() {
        // Arrange
        LoginDto loginDto = new LoginDto("nonexistent@example.com", "password");
        when(usersService.findByEmail(loginDto.getEmail())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> authController.login(loginDto));
    }
    
}