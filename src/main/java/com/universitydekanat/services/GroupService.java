package com.universitydekanat.services;

import com.universitydekanat.dtos.GroupCreateRequest;
import com.universitydekanat.dtos.GroupFullDto;

import java.util.List;

public interface GroupService {
    public GroupFullDto create(GroupCreateRequest groupCreateRequest);

    public List<GroupFullDto> getAll();

    public List<GroupFullDto> getAllByTitle(String title);


    public void deleteById(int id);

    public GroupFullDto getById(int id);

    public GroupFullDto getByIdAndLastName(int id, String lastName);
}
