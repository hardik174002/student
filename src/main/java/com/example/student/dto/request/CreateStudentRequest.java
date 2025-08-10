package com.example.student.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStudentRequest {
    
    @NotBlank(message  = "Must not be blank")
    private String address;

    @NotBlank(message  = "Must not be blank")
    private String dob;

    @NotBlank(message  = "Must not be blank")
    private String name;

    @Min(1)
    private Integer semester;
}
