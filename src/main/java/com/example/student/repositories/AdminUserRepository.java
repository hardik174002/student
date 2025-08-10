package com.example.student.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entities.AdminUsers;

public interface AdminUserRepository extends JpaRepository<AdminUsers,Long>{
    Optional<AdminUsers> findByUsername(String username);
}
