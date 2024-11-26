/*package com.example.MSUser.services;

import com.example.MSUser.models.User;
import com.example.MSUser.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldSaveUserWhenEmailNotInUse() {
        // Arrange
        User user = new User();
        user.setEmail("mockitotest@example.com");
        user.setPassword("rawPassword");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User savedUser = userService.register(user);

        // Assert
        assertNotNull(savedUser);
        assertNotEquals("rawPassword", savedUser.getPassword()); // Ensure the password is encoded
        verify(userRepository, times(1)).save(user);
    }



    @Test
    void register_shouldThrowExceptionWhenEmailAlreadyInUse() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(new User()));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.register(user));
        verify(userRepository, never()).save(any());
    }


    @Test
    void authenticate_shouldReturnUserWhenCredentialsAreValid() {
        // Arrange
        String rawPassword = "rawPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword); // Encode password once
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword(encodedPassword);

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.authenticate("test@example.com", rawPassword);

        // Assert
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }


    @Test
    void authenticate_shouldReturnEmptyWhenCredentialsAreInvalid() {
        // Arrange
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.authenticate("test@example.com", "wrongPassword");

        // Assert
        assertFalse(result.isPresent());
    }
}
*/