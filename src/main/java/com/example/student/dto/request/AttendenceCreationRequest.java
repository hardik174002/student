package com.example.student.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AttendenceCreationRequest {
    
    @JsonProperty("student_id")
    private Integer studentId;

    
    @JsonProperty("is_present")
    private boolean isPresent;
}
