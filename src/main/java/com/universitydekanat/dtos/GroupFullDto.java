package com.universitydekanat.dtos;

import com.universitydekanat.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GroupFullDto {
    private Integer id;

    private String nameOfGroup;

    private LocalDate creationDate;

    private LocalDate updateDate;
    private List<StudentFullDto> students;
}
