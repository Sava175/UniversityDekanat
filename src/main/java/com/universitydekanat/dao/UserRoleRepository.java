package com.universitydekanat.dao;

import com.universitydekanat.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    UserRole findByName(String name);
}
