package com.example.userapi.service;

import com.example.userapi.model.XUser;
import com.example.userapi.repositories.XUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final XUserRepository userRepo;

    public UserService(XUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public XUser getUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }
}
