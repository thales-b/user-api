package com.example.userapi.repositories;

import com.example.userapi.model.XUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface XUserRepository extends JpaRepository<XUser, Long> {
    boolean existsByUsername(String username);
    Optional<XUser> findByUsername(String username);
}
