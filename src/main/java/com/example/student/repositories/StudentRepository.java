package com.example.student.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student.dto.projections.StudentWithCourse;
import com.example.student.entities.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student",nativeQuery = true)
    public void doTruncate();

    @Query(value = """
            SELECT student_info.name, course.course_code
            FROM student_info 
            LEFT JOIN student ON (student_info.id = student.student_info_id) 
            LEFT JOIN course ON (student.semester = course.semester) 
            WHERE student_info.name LIKE CONCAT('%', :name, '%')"""
            , nativeQuery = true)
    List<StudentWithCourse> getStudentWithCourseDetails(@Param("name") String name);

}
