package com.universitydekanat.services;

import com.universitydekanat.dao.GroupsRepository;
import com.universitydekanat.dao.StudentRepository;
import com.universitydekanat.domain.Group;
import com.universitydekanat.domain.Student;
import com.universitydekanat.dtos.StudentCreateRequestDto;
import com.universitydekanat.dtos.StudentFullDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public void createStudent(StudentCreateRequestDto studentCreateRequestDto, int groupNumber) {
        Group group = groupsRepository.findById(groupNumber).get();


        Student student = new Student();
        student.setEmail(studentCreateRequestDto.getEmail());
        student.setFirstName(studentCreateRequestDto.getFirstName());
        student.setLastName(studentCreateRequestDto.getLastName());
        student.setPhone(studentCreateRequestDto.getPhone());
        student.setUpdated(LocalDateTime.now());
        student.setCreated(LocalDateTime.now());
        student.setAverageGrade(studentCreateRequestDto.getAverageGrade());
        student.setGroupNumber(group.getNameOfGroup());
        student.setDateOfBirth(LocalDate.parse(studentCreateRequestDto.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));


        student = studentRepository.save(student);

        group.getStudents().add(student);
        groupsRepository.save(group);

    }


    @Override
    public List<StudentFullDto> getAllStudents(int groupNumber) {
        Group group = groupsRepository.findById(groupNumber).get();
        return group.getStudents().stream()
                .map(student ->
                        new StudentFullDto()
                                .setId(student.getId())
                                .setEmail(student.getEmail())
                                .setGroupNumber(student.getGroupNumber())
                                .setPhone(student.getPhone())
                                .setCreated(student.getCreated())
                                .setUpdated(student.getUpdated())
                                .setAverageGrade(student.getAverageGrade())
                                .setDateOfBirth(student.getDateOfBirth())
                                .setFirstName(student.getFirstName())
                                .setLastName(student.getLastName())

                ).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id, int sid) {
        Group group = groupsRepository.findById(id).get();
        Student student = group.getStudents().stream().filter(st->st.getId()==sid ).findFirst().get();
        group.getStudents().remove(student);
        groupsRepository.save(group);
        studentRepository.deleteById(sid);
    }
}
