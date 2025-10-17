// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.repository;

import com.example.mvcapp.model.WikiAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for WikiAdmin database operations
 * BIT235 Assessment 2 - Part 1
 */
@Repository
public interface WikiAdminRepository extends JpaRepository<WikiAdmin, Long> {
    
    /**
     * Find WikiAdmin by username
     * @param username the username to search
     * @return Optional containing WikiAdmin if found
     */
    Optional<WikiAdmin> findByUsername(String username);
    
    /**
     * Check if username exists
     * @param username the username to check
     * @return true if username exists
     */
    boolean existsByUsername(String username);
}
