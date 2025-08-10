package com.example.student.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;


    @OneToOne(orphanRemoval = false,cascade = CascadeType.ALL) // Question 1) What if we use cascase only holder or vice a versa
    @JsonBackReference
    private StudentInfo studentInfo;

    private Integer semester;

    @ManyToMany
    @JoinTable(
        name = "student_archievement_table",
        joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "archievement_id")
    )
    List<Archievements> archievements;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Atendence> atendences;
}
