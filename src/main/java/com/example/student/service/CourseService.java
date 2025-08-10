package com.example.student.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.student.entities.Course;
import com.example.student.repositories.CourseRepos;

import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class CourseService {

    private CourseRepos courseRepos;

    public CourseService(CourseRepos courseRepos){
        this.courseRepos = courseRepos;
    }

    public Set<Course> getAllCourses(){
        List<Course> courses = courseRepos.findAll();
        return new HashSet<>(courses);
    }

    public Set<Course> getAllCoursesBySemester(@NotNull int semester_id){
        List<Course> courses = courseRepos.findBySemester(String.valueOf(semester_id));
        return new HashSet<>(courses);
    }
    
}
