package com.example.userapi.service;

import com.example.userapi.dto.XUserDTO;
import com.example.userapi.dto.XUserMapper;
import com.example.userapi.exception.UserAlreadyExistsException;
import com.example.userapi.exception.UserNotFoundException;
import com.example.userapi.exception.UserValidationException;
import com.example.userapi.model.XUser;
import com.example.userapi.repositories.XUserRepository;
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

    @Test
    void testGetUser() {
        XUser existing = XUser.builder()
                .username("existing")
                .birthDate(LocalDate.of(2000,1,1))
                .country("France")
                .build();
        userRepo.save(existing);

        XUserDTO dto = userService.getUser("existing");

        assertNotNull(dto);
        assertEquals(existing.getUsername(), dto.getUsername());
    }

    @Test
    void testGetUser_NotFound() {
        assertThrows(UserNotFoundException.class,  () -> userService.getUser("404"));
    }

    @Test
    void testRegisterUser() {
        XUserDTO dto = XUserDTO.builder()
                .username("username")
                .birthDate(LocalDate.of(2000,1,1))
                .country("France")
                .build();

        userService.registerUser(dto);

        assertNotNull(userService.getUser("username"));
    }

    @Test
    void testRegisterUser_InvalidUsers() {
        XUserDTO minor = XUserDTO.builder()
                .username("1")
                .birthDate(LocalDate.now())
                .country("France")
                .build();
        assertThrows(UserValidationException.class,  () -> userService.registerUser(minor));

        XUserDTO nonFrench = XUserDTO.builder()
                .username("2")
                .birthDate(LocalDate.of(2000,1,1))
                .country("Belgium")
                .build();
        assertThrows(UserValidationException.class,  () -> userService.registerUser(nonFrench));
    }

    @Test
    void testRegisterUser_AlreadyExists() {
        XUserDTO newUser = XUserDTO.builder()
                .username("new")
                .birthDate(LocalDate.of(2000,1,1))
                .country("France")
                .build();
        userService.registerUser(newUser);
        assertNotNull(userService.getUser("new"));

        assertThrows(UserAlreadyExistsException.class,  () -> userService.registerUser(newUser));
    }
}
