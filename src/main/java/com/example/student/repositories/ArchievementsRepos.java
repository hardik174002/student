package com.example.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.student.entities.Archievements;

import jakarta.transaction.Transactional;

@Repository
public interface ArchievementsRepos extends JpaRepository<Archievements,Integer>{
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM archievements",nativeQuery = true)
    public void doTruncate();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student_archievement_table",nativeQuery = true)
    public void doTruncateRelationaltable();
}
