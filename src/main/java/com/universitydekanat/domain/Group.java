package com.universitydekanat.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gp")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String nameOfGroup;

   @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private LocalDate updateDate;


    @OneToMany(fetch = FetchType.EAGER)
    private List <Student> students;



}
