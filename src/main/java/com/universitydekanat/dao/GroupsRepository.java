package com.universitydekanat.dao;

import com.universitydekanat.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Group,Integer> {

    List<Group> findAllByNameOfGroupContainsIgnoreCase(String name);
}
