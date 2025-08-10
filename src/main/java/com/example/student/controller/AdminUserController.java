package com.example.student.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.dto.request.CreateAdminUser;
import com.example.student.entities.AdminUsers;
import com.example.student.repositories.AdminUserRepository;
import com.example.student.service.AdminUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/")
public class AdminUserController {

    private AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService){
        this.adminUserService = adminUserService;
    }

    @PostMapping("create-admin")
    public ResponseEntity<AdminUsers> createAdminUsers(@RequestBody @Valid CreateAdminUser createAdminUser){
        return new ResponseEntity<AdminUsers>(adminUserService.createAdminUser(createAdminUser), HttpStatus.CREATED);
    }
    
}
