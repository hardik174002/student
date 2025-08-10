package com.example.student.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.student.entities.AdminUsers;
import com.example.student.repositories.AdminUserRepository;

@Component
public class ApplicationSecurityUserService implements UserDetailsService{

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AdminUsers> adminUsers = adminUserRepository.findByUsername(username);
        if(adminUsers!=null){
            return adminUsers.get();
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }
    
}
