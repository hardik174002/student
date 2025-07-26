package com.example.student.entities;

import jakarta.persistence.*;

@Entity
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    private Integer studentId;

}
