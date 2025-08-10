package com.example.student.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.student.dto.request.CreateStudentRequest;
import com.example.student.dto.request.PageableRequest;
import com.example.student.dto.response.PageResponse;
import com.example.student.dto.response.Response;
import com.example.student.entities.Course;
import com.example.student.service.CourseService;
import com.example.student.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/admin")
@Slf4j
public class StudentCreationController {

    private StudentService studentService;

    private CourseService courseService;

    public StudentCreationController(StudentService service,CourseService courseService){
        studentService = service;
        this.courseService = courseService;
    }

    @GetMapping("/check")
    public String check(Authentication authentication) {
        log.info("User: {}", authentication.getName());
        log.info("Authorities: {}", authentication.getAuthorities());
        return "ok";
    }
    
    @PostMapping("/create-student")
    public ResponseEntity<?> createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
        log.info("Role Of Current Udser = ",SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(createStudentRequest));
    }

    @GetMapping("/get-all-students")
    public String getAllStudents(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/get-all-course")
    public ResponseEntity<Set<Course>> postMethodName() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/get-courses-by-semester/{semester_id}")
    public ResponseEntity<Set<Course>> getMethodName(@Valid @Min(1) @PathVariable("semester_id") int semester_id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCoursesBySemester(semester_id));
    }
    
    @GetMapping("/student-details-by-name/{student_name}")
    public ResponseEntity<Response> getStudentDetails(@PathVariable("student_name") String student_name) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentDetailsWithCourseDetails(student_name));
    }
    
    @PostMapping("/pageable-data-student")
    public ResponseEntity<PageResponse> getPagebaleStudentDetails(@RequestBody PageableRequest entity) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getPagebaleStudentDetails(entity));
    }
    
}
