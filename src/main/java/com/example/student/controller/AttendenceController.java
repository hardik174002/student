package com.example.student.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.student.dto.request.AttendenceCreationRequest;
import com.example.student.service.AttendenceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/student/attendence")
public class AttendenceController {

    private AttendenceService attendenceService;

    public AttendenceController(AttendenceService attendenceService){
        this.attendenceService = attendenceService;
    }
    
    @PostMapping("/add")
    public ResponseEntity createAttendence(@RequestBody AttendenceCreationRequest request) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(attendenceService.createAttendence(request));
        }catch(Exception exception){
            return ResponseEntity.status(500).body(exception.getLocalizedMessage());
        }
    }
    
}
