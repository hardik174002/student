package com.example.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entities.Atendence;

public interface AttendenceRepository extends JpaRepository<Atendence,Integer>{
    
}
