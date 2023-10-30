package com.universitydekanat.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StudentCreateRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private double averageGrade;

}
