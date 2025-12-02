package com.example.userapi.controller;

import com.example.userapi.dto.XUserDTO;
import com.example.userapi.exception.UserAlreadyExistsException;
import com.example.userapi.exception.UserNotFoundException;
import com.example.userapi.exception.UserValidationException;
import com.example.userapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private XUserDTO user;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        user = XUserDTO.builder()
                .username("username")
                .birthDate(LocalDate.of(2000, 1, 1))
                .country("France")
                .build();
    }

    @Test
    void getUser() throws Exception {
        when(userService.getUser("username")).thenReturn(user);

        mockMvc.perform(get("/api/users/username"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.birthDate").value(LocalDate.of(2000, 1, 1).toString()))
                .andExpect(jsonPath("$.country").value("France"));
    }

    @Test
    void getUser_404() throws Exception {
        when(userService.getUser("username")).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/users/username"))
                .andExpect(status().isNotFound());
    }

    @Test
    void registerUser() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void registerUser_409() throws Exception {
        when(userService.registerUser(any(XUserDTO.class))).thenThrow(UserAlreadyExistsException.class);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    void registerUser_400() throws Exception {
        when(userService.registerUser(any(XUserDTO.class))).thenThrow(UserValidationException.class);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }
}
