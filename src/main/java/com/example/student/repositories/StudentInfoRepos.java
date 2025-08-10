package com.example.student.repositories;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entities.StudentInfo;

public interface StudentInfoRepos extends JpaRepository<StudentInfo,Integer>{
    
}
