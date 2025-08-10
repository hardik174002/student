package com.example.student.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.student.dto.request.AttendenceCreationRequest;
import com.example.student.entities.Atendence;
import com.example.student.entities.Student;
import com.example.student.repositories.AttendenceRepository;
import com.example.student.repositories.StudentRepository;

@Service
public class AttendenceService {

    private AttendenceRepository attendenceRepository;
    private StudentRepository studentRepository;

    public AttendenceService(AttendenceRepository attendenceRepository,StudentRepository studentRepository){
        this.attendenceRepository = attendenceRepository;
        this.studentRepository = studentRepository;
    }
    
    public AttendenceCreationRequest createAttendence(AttendenceCreationRequest request) throws Exception{
        Optional<Student> studentDetails = studentRepository.findById(request.getStudentId());
        if(studentDetails.isPresent()){
            Atendence atendence = new Atendence();
            atendence.setPresent(request.isPresent());
            atendence.setStudent(studentDetails.get());
            atendence.setDateOfAttendence(LocalDate.now());
            attendenceRepository.save(atendence);
        }else{
            throw new Exception("Student Not present");
        }
        return request;
    }
}
