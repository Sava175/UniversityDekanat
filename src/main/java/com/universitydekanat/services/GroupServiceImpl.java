package com.universitydekanat.services;

import com.universitydekanat.dao.GroupsRepository;
import com.universitydekanat.domain.Group;
import com.universitydekanat.domain.Student;
import com.universitydekanat.dtos.GroupCreateRequest;
import com.universitydekanat.dtos.GroupFullDto;
import com.universitydekanat.dtos.StudentFullDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public GroupFullDto create(GroupCreateRequest groupCreateRequest) {
        Group group = new Group();
        group.setNameOfGroup(groupCreateRequest.getTitle());
        group.setCreationDate(LocalDate.now());
        group.setStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.now());

        group = groupsRepository.save(group);


        return new GroupFullDto()
                .setId(group.getId())
                .setNameOfGroup(group.getNameOfGroup())
                .setCreationDate(group.getCreationDate())
                .setUpdateDate(group.getUpdateDate())
                .setStudents(new ArrayList<>());
    }

    @Override
    public List<GroupFullDto> getAll() {

        List<Group> groupList = groupsRepository.findAll();
        return groupList.stream()
                .map(group ->
                        new GroupFullDto()
                                .setId(group.getId())
                                .setNameOfGroup(group.getNameOfGroup())
                                .setCreationDate(group.getCreationDate())
                                .setUpdateDate(group.getUpdateDate())
                                .setStudents(group.getStudents().stream()
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

                                        ).collect(Collectors.toList()))

                ).collect(Collectors.toList());

    }

    @Override
    public void deleteById(int id) {
        groupsRepository.deleteById(id);
    }

    @Override
    public GroupFullDto getById(int id) {

        Group group = groupsRepository.findById(id).get();


        return new GroupFullDto()
                .setId(group.getId())
                .setNameOfGroup(group.getNameOfGroup())
                .setCreationDate(group.getCreationDate())
                .setUpdateDate(group.getUpdateDate())
                .setStudents(group.getStudents().stream()
                        .map(student ->
                                new StudentFullDto()
                                        .setId(student.getId())
                                        .setEmail(student.getEmail())
                                        .setGroupNumber(student.getGroupNumber())
                                        .setPhone(student.getPhone())
                                        .setCreated(student.getCreated())
                                        .setUpdated(student.getUpdated())
                                        .setAverageGrade(student.getAverageGrade())
                                        .setFirstName(student.getFirstName())
                                        .setLastName(student.getLastName())
                                        .setDateOfBirth(student.getDateOfBirth())

                        ).collect(Collectors.toList()));
    }

    @Override
    public List<GroupFullDto> getAllByTitle(String title) {
        List<Group> groupList = groupsRepository.findAllByNameOfGroupContainsIgnoreCase(title);
        return groupList.stream()
                .map(group ->
                        new GroupFullDto()
                                .setId(group.getId())
                                .setNameOfGroup(group.getNameOfGroup())
                                .setCreationDate(group.getCreationDate())
                                .setUpdateDate(group.getUpdateDate())
                                .setStudents(group.getStudents().stream()
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

                                        ).collect(Collectors.toList()))

                ).collect(Collectors.toList());
    }

    @Override
    public GroupFullDto getByIdAndLastName(int id, String lastName) {
        Group group = groupsRepository.findById(id).get();

        group.getStudents().removeIf(st -> !st.getLastName().contains(lastName));


        return new GroupFullDto()
                .setId(group.getId())
                .setNameOfGroup(group.getNameOfGroup())
                .setCreationDate(group.getCreationDate())
                .setUpdateDate(group.getUpdateDate())
                .setStudents(group.getStudents().stream()
                        .map(student ->
                                new StudentFullDto()
                                        .setId(student.getId())
                                        .setEmail(student.getEmail())
                                        .setGroupNumber(student.getGroupNumber())
                                        .setPhone(student.getPhone())
                                        .setCreated(student.getCreated())
                                        .setUpdated(student.getUpdated())
                                        .setAverageGrade(student.getAverageGrade())
                                        .setFirstName(student.getFirstName())
                                        .setLastName(student.getLastName())
                                        .setDateOfBirth(student.getDateOfBirth())

                        ).collect(Collectors.toList()));
    }
}
