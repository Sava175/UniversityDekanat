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
public class StudentFullDto {


    private Integer id;

    private String firstName;
    private String lastName;

    private String groupNumber;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private double averageGrade;

    private LocalDateTime created;
    private LocalDateTime updated;
}
