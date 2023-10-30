package com.universitydekanat.dao;


import com.universitydekanat.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,Integer> {
}
