package com.example.userapi.dto;

import com.example.userapi.model.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class XUserDTO {
    private Long id;

    @NotBlank
    private String username;

    @NonNull
    private LocalDate birthDate;

    private String phoneNumber;

    private Gender gender;
}
