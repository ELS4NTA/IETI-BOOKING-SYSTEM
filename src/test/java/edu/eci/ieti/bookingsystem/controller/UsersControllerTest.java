package edu.eci.ieti.bookingsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.eci.ieti.bookingsystem.controller.user.UsersController;
import edu.eci.ieti.bookingsystem.exception.UserNotFoundException;
import edu.eci.ieti.bookingsystem.repository.user.UserDocument;
import edu.eci.ieti.bookingsystem.repository.user.UserDto;
import edu.eci.ieti.bookingsystem.service.user.UsersService;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    @InjectMocks
    UsersController usersController;

    @Mock
    UsersService usersService;

    @Test
    void testCreateUser() {
        // Arrange
        UserDto userDto = new UserDto("Pepe", "Azul", "pepe.azul@dominio.com", "123456789");
        UserDocument newUserDocument = new UserDocument(userDto);
        when(usersService.save(any(UserDocument.class))).thenReturn(newUserDocument);

        // Act
        ResponseEntity<UserDocument> response = usersController.createuser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newUserDocument, response.getBody());
        verify(usersService, times(1)).save(any(UserDocument.class));
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        List<UserDocument> userDocuments = Arrays.asList(
                new UserDocument(new UserDto("Pepe", "Azul", "pepe.azul@dominio.com", "123456789")),
                new UserDocument(new UserDto("Daniel", "Santanilla", "daniel.santanilla@dominio.com", "987654321")));
        when(usersService.all()).thenReturn(userDocuments);

        // Act
        ResponseEntity<List<UserDocument>> response = usersController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDocuments, response.getBody());
        verify(usersService, times(1)).all();
    }

    @Test
    void testFindById() {
        // Arrange
        String userId = "123";
        UserDocument userDocument = new UserDocument(new UserDto("Pepe", "Azul", "pepe.azul@dominio.com", "123456789"));
        when(usersService.findById(userId)).thenReturn(Optional.of(userDocument));

        // Act
        ResponseEntity<UserDocument> response = usersController.findById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDocument, response.getBody());
        verify(usersService, times(1)).findById(userId);
    }

    @Test
    void testFindByIdUserNotFound() {
        // Arrange
        String userId = "123";
        when(usersService.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> usersController.findById(userId));
        verify(usersService, times(1)).findById(userId);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        String userId = "123";
        UserDto userDto = new UserDto("Pepe", "Azul", "pepe.azul@dominio.com", "123456789");
        UserDocument existingUserDocument = new UserDocument(userDto);
        when(usersService.findById(userId)).thenReturn(Optional.of(existingUserDocument));
        when(usersService.save(any(UserDocument.class))).thenReturn(existingUserDocument);

        // Act
        ResponseEntity<UserDocument> response = usersController.updateUser(userId, userDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingUserDocument, response.getBody());
        verify(usersService, times(1)).findById(userId);
        verify(usersService, times(1)).save(any(UserDocument.class));
    }

    @Test
    void testUpdateUserUserNotFound() {
        // Arrange
        String userId = "123";
        UserDto userDto = new UserDto("Pepe", "Azul", "pepe.azul@dominio.com", "123456789");
        when(usersService.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> usersController.updateUser(userId, userDto));
        verify(usersService, times(1)).findById(userId);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        String userId = "123";
        UserDocument userDocument = new UserDocument(new UserDto("Pepe", "Azul", "pepe.azul@dominio.com", "123456789"));
        when(usersService.findById(userId)).thenReturn(Optional.of(userDocument));

        // Act
        ResponseEntity<Void> response = usersController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usersService, times(1)).findById(userId);
        verify(usersService, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUserUserNotFound() {
        // Arrange
        String userId = "123";
        when(usersService.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> usersController.deleteUser(userId));
        verify(usersService, times(1)).findById(userId);
    }

}