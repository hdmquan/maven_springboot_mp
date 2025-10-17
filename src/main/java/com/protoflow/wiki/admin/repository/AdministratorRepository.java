// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.repository;

import com.protoflow.wiki.admin.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Administrator entity database operations
 * BIT235 Assessment 2 - Part 1
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    
    /**
     * Find administrator by username
     * @param username the username to search
     * @return Optional containing Administrator if found
     */
    Optional<Administrator> findByUsername(String username);
    
    /**
     * Check if username exists
     * @param username the username to check
     * @return true if username exists
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if email exists
     * @param email the email to check
     * @return true if email exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Find active administrators
     * @return list of active administrators
     */
    List<Administrator> findByIsActiveTrue();
    
    /**
     * Find administrators by full name containing text
     * @param fullName the text to search for
     * @return list of matching administrators
     */
    List<Administrator> findByFullNameContainingIgnoreCase(String fullName);
    
    /**
     * Count active administrators
     * @return number of active administrators
     */
    long countByIsActiveTrue();
    
    /**
     * Find administrators who logged in after specified date
     * @param date the date to check
     * @return list of administrators
     */
    @Query("SELECT a FROM Administrator a WHERE a.lastLoginAt > :date")
    List<Administrator> findRecentlyActiveAdministrators(@Param("date") LocalDateTime date);
}
