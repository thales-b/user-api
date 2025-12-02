package com.example.userapi.dto;

import com.example.userapi.model.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class XUserDTO {
    @NotBlank
    private String username;

    @NonNull
    private LocalDate birthDate;

    @NotBlank
    private String country;

    private String phoneNumber;

    private Gender gender;
}
