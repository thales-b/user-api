package com.example.userapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// User is a reserved keyword in most SQL databases
public class XUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private LocalDate birthdate;

    // The country could also be represented by an enum
    // in a more fleshed-out project
    @Column(nullable = false)
    private String country;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
