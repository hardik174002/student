package com.example.student.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.student.dto.request.CreateStudentRequest;
import com.example.student.dto.request.PageableRequest;
import com.example.student.dto.response.PageResponse;
import com.example.student.dto.response.Response;
import com.example.student.entities.Student;
import com.example.student.entities.StudentInfo;
import com.example.student.repositories.StudentInfoRepos;
import com.example.student.repositories.StudentRepository;

import jakarta.validation.constraints.NotNull;

@Service
@Validated
@PreAuthorize("hasAnyRole('CO_PILOT','SUPER_ADMIN')")
public class StudentService {

    private StudentRepository studentRepository;
    private StudentInfoRepos studentInfoRepos;

    public StudentService(StudentRepository studentRepository,StudentInfoRepos studentInfoRepos){
        this.studentRepository = studentRepository;
        this.studentInfoRepos = studentInfoRepos;
    }
    public StudentInfo addStudent(@NotNull CreateStudentRequest createStudentRequest){
        StudentInfo studentInfo = new StudentInfo();
        Student student = new Student();
        BeanUtils.copyProperties(createStudentRequest, studentInfo);
        student.setStudentInfo(studentInfo);
        student.setSemester(createStudentRequest.getSemester());
        studentRepository.save(student);
        return studentInfo;
    }
    public Response getStudentDetailsWithCourseDetails(@NotNull String name){
        Response response = new Response();
        response.setData(studentRepository.getStudentWithCourseDetails(name));
        return response;
    }
    public PageResponse getPagebaleStudentDetails(PageableRequest pageableRequest){
        Page<StudentInfo> studentInfo = studentInfoRepos.findAll(PageRequest.of(pageableRequest.getPageNo(), pageableRequest.getNoOfRecords(),Sort.by("id").descending().and(Sort.by("name"))));
        PageResponse pageResponse = new PageResponse();
        pageResponse.setData(studentInfo.getContent());
        pageResponse.setTotalRecords(pageResponse.getTotalRecords());
        return pageResponse;
    }
}
