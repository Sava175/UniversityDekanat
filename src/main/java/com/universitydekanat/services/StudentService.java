package com.universitydekanat.services;

import com.universitydekanat.dtos.StudentCreateRequestDto;
import com.universitydekanat.dtos.StudentFullDto;

import java.util.List;

public interface StudentService {

    void createStudent(StudentCreateRequestDto studentCreateRequestDto, int groupNumber);

    List<StudentFullDto> getAllStudents( int groupNumber);
    void deleteById(int id, int sid);
}
