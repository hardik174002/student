package com.example.student.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.student.entities.Course;

import jakarta.transaction.Transactional;

@Repository
public interface CourseRepos extends JpaRepository<Course,Integer>{

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM course",nativeQuery = true)
    public void doTruncate();

    Optional<Course> findById(Integer id);

    List<Course> findAll();

    List<Course> findBySemester(String semester);

} 
