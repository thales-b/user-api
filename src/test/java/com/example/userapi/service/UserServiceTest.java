package com.example.userapi.service;

import com.example.userapi.dto.XUserDTO;
import com.example.userapi.dto.XUserMapper;
import com.example.userapi.exception.UserNotFoundException;
import com.example.userapi.model.XUser;
import com.example.userapi.repositories.XUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
class UserServiceTest {
    @Autowired
    XUserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private XUserMapper userMapper;

    private XUser existing;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetUser() {
        existing = XUser.builder()
                .username("username")
                .birthDate(LocalDate.of(2000,1,1))
                .country("France")
                .build();
        userRepo.save(existing);
        Long id = existing.getId();

        XUserDTO dto = userService.getUser(id);

        assertNotNull(dto);
        assertEquals(existing.getUsername(), dto.getUsername());
    }

    @Test
    void testGetUser_notFound() {
        assertThrows(UserNotFoundException.class,  () -> userService.getUser(404L));
    }

    @Test
    void testRegisterUser() {
        XUserDTO dto = XUserDTO.builder()
                .username("username")
                .birthDate(LocalDate.of(2000,1,1))
                .country("France")
                .build();

        userService.registerUser(dto);


    }
}
