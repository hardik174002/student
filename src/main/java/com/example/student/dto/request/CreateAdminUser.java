package com.example.student.dto.request;

import com.example.student.custom_validators.PasswordValidator;
import com.example.student.enums.AdminRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAdminUser {
    
    @NotBlank
    @Size(min = 3,max = 15,message = "Min Length is 3 and max is 15 for username")
    private String username;

    @PasswordValidator
    private String password;

    private String name;

    @Email
    private String email;

    @JsonProperty("phone_number")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @JsonProperty("role")
    private AdminRoles adminRoles;
}
