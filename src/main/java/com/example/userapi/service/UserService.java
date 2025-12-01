package com.example.userapi.service;

import com.example.userapi.dto.XUserDTO;
import com.example.userapi.dto.XUserMapper;
import com.example.userapi.exception.UserAlreadyExistsException;
import com.example.userapi.exception.UserNotFoundException;
import com.example.userapi.exception.UserValidationException;
import com.example.userapi.model.XUser;
import com.example.userapi.repositories.XUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class UserService {
    private final XUserRepository userRepo;
    private final XUserMapper userMapper;

    public UserService(XUserRepository userRepo, XUserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public XUserDTO getUser(String username) {
        XUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

    public XUserDTO registerUser(XUserDTO userDTO) {
        String username = userDTO.getUsername();
        if (userRepo.existsByUsername(username))
            throw new UserAlreadyExistsException("User '" + username + "' already exists");

        String country = userDTO.getCountry();
        LocalDate birthDate = userDTO.getBirthDate();
        long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        if (age < 18 || !country.equalsIgnoreCase("france"))
            throw new UserValidationException("User must be an adult French resident");

        XUser newUser = userMapper.toEntity(userDTO);
        userRepo.save(newUser);
        return userMapper.toDTO(newUser);
    }
}
