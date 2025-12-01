package com.example.userapi.repositories;

import com.example.userapi.model.Gender;
import com.example.userapi.model.XUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class XUserRepositoryTest {
    @Autowired
    private XUserRepository repo;

    private XUser u;

    @BeforeEach
    void setUp() {
        u = XUser.builder()
                .username("username")
                .birthDate(LocalDate.of(2000,1,1))
                .country("France")
                .phoneNumber("+33 777 777 777")
                .gender(Gender.MALE)
                .build();
    }

    @Test
    void testCreate() {
        XUser created = repo.save(u);
        XUser retrieved = repo.findById(created.getId()).get();
        assertEquals(created, retrieved);
    }

    @Test
    void testCreate_MissingRequiredFields() {
        XUser u2 = new XUser();
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(u2));
    }

    @Test
    void testCreate_DuplicateUsernames() {
        XUser u2 = XUser.builder()
                .username("username")
                .birthDate(LocalDate.now())
                .country("Italy")
                .phoneNumber("+39 06 99999999")
                .gender(Gender.MALE)
                .build();
        repo.save(u);
        assertThrows(DataIntegrityViolationException.class, () -> repo.save(u2));
    }

    @Test
    void testUpdate() {
        XUser created = repo.save(u);
        assertEquals("username", created.getUsername());
        created.setUsername("updated");
        repo.save(created);
        assertEquals("updated", created.getUsername());
    }

    @Test
    void testDelete() {
        XUser created = repo.save(u);
        Long createdId = created.getId();
        repo.delete(created);
        assertTrue(repo.findById(createdId).isEmpty());
    }
}
