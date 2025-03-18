package com.example.ecommerce.services;

import com.example.ecommerce.dtos.UserDTO;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.mappers.UserMapper;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encoded_password");

        when(userMapper.toEntity(any(UserDTO.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);

        // Act
        UserDTO result = userService.registerUser(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals(userDTO.getEmail(), result.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void login_Success() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encoded_password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // Act
        String token = userService.login(userDTO);

        // Assert
        assertNotNull(token);
    }

    @Test
    void login_UserNotFound() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("nonexistent@example.com");
        userDTO.setPassword("password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.login(userDTO));
    }
}