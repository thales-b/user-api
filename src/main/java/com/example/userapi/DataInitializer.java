package com.example.userapi;

import com.example.userapi.model.XUser;
import com.example.userapi.repositories.XUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final XUserRepository repo;

    @Override
    public void run(String... args) throws Exception {
        if (repo.count() == 0) {
            XUser u1 = XUser.builder()
                    .username("JohnDoe")
                    .birthDate(LocalDate.of(1980,3,12))
                    .country("France")
                    .build();
            repo.save(u1);
        }
    }
}
