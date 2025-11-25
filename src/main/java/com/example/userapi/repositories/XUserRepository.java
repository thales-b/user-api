package com.example.userapi.repositories;

import com.example.userapi.model.XUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XUserRepository extends JpaRepository<XUser, Long> {

}
