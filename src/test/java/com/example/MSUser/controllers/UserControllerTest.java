package com.example.MSUser.controllers;

import com.example.MSUser.dtos.LoginRequest;
import com.example.MSUser.dtos.RegisterRequest;
import com.example.MSUser.dtos.UpdateUserRequest;
import com.example.MSUser.models.User;
import com.example.MSUser.repositories.UserRepository;
import com.example.MSUser.services.UserService;
import com.example.MSUser.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UsersControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private static JwtUtils jwtUtils;

    @InjectMocks
    private UsersController usersController;


    private static final String TOKEN_PREFIX = "Bearer ";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();

        User mockUser = new User();
        mockUser.setUser_id(1L);
        mockUser.setEmail("test@example.com");

        when(userService.authenticate("test@example.com", "password"))
                .thenReturn(Optional.of(mockUser));

        when(jwtUtils.generateToken(mockUser)).thenReturn("fake-jwt-token");
    }

    @Test
    void register_shouldRegisterUser() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("test@example.com", "password123");
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());

        when(userService.register(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").value(user.getPassword()));

        verify(userService, times(1)).register(any(User.class));
    }

    @Test
    void login_shouldReturnJwtWhenCredentialsAreValid() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword("encodedPassword");

        when(userService.authenticate(eq("test@example.com"), eq("password123"))).thenReturn(Optional.of(user));
        when(jwtUtils.generateToken(user)).thenReturn("mockedToken");

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("mockedToken"));

        verify(userService, times(1)).authenticate(eq("test@example.com"), eq("password123"));
        verify(jwtUtils, times(1)).generateToken(user);
    }

    /*@Test
    void getUserById_shouldReturnUser() throws Exception {
        User user = new User();
        user.setUser_id(1L);
        user.setEmail("test@example.com");

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1")
                        .header("Authorization", TOKEN_PREFIX + "fake-jwt-token")) // Ajout de l'en-tête avec le token simulé
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()));

        verify(userService, times(1)).getUserById(1L);
    }*/

    @Test
    void login_shouldReturnToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        User mockUser = new User();
        mockUser.setUser_id(1L);
        mockUser.setEmail("test@example.com");

        when(userService.authenticate("test@example.com", "password"))
                .thenReturn(Optional.of(mockUser));
        when(jwtUtils.generateToken(mockUser)).thenReturn("fake-jwt-token");

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("fake-jwt-token"));
    }


    /*@Test
    void updateUser_shouldReturnUpdatedUser() throws Exception {
        User existingUser = new User();
        existingUser.setUser_id(1L);
        existingUser.setEmail("test@example.com");

        when(userService.getUserById(1L)).thenReturn(Optional.of(existingUser));
        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(existingUser);

        UpdateUserRequest updateRequest = new UpdateUserRequest();
        updateRequest.setFirstName("John");

        mockMvc.perform(put("/api/users/1")
                        .header("Authorization", TOKEN_PREFIX + "fake-jwt-token") // Ajout de l'en-tête Authorization
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));

        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }


    @Test
    void deleteUser_shouldReturnNoContent() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1")
                        .header("Authorization", TOKEN_PREFIX + "fake-jwt-token")) // Ajout de l'en-tête Authorization
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(eq(1L));
    }*/

}
