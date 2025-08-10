package com.example.student.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.student.dto.request.CreateAdminUser;
import com.example.student.entities.AdminUsers;
import com.example.student.repositories.AdminUserRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
@Validated
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class AdminUserService {

    private AdminUserRepository adminUserRepository;

    private PasswordEncoder passwordEncoder;

    public AdminUserService(AdminUserRepository adminUserRepository,PasswordEncoder passwordEncoder){
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminUsers createAdminUser(CreateAdminUser createAdminUser){
        AdminUsers adminUsers = new AdminUsers();
        BeanUtils.copyProperties(createAdminUser, adminUsers);
        adminUsers.setPassword(passwordEncoder.encode(createAdminUser.getPassword()));
        return adminUserRepository.save(adminUsers);
    }
    
}
