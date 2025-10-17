// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.service;

import com.protoflow.wiki.admin.model.Administrator;
import com.protoflow.wiki.admin.repository.AdministratorRepository;
import com.protoflow.wiki.admin.util.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Administrator business logic
 * BIT235 Assessment 2 - Part 1
 */
@Service
@Transactional
public class AdministratorService {
    
    private final AdministratorRepository administratorRepository;
    
    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }
    
    /**
     * Authenticate administrator with username and password
     * @param username the username
     * @param password the plain text password
     * @return Optional containing Administrator if authentication successful
     */
    @Transactional(readOnly = true)
    public Optional<Administrator> authenticateAdministrator(String username, String password) {
        Optional<Administrator> adminOptional = administratorRepository.findByUsername(username);
        
        if (adminOptional.isPresent()) {
            Administrator admin = adminOptional.get();
            if (admin.getIsActive() && PasswordUtility.verifyPassword(password, admin.getPasswordHash())) {
                // Update last login time
                admin.setLastLoginAt(LocalDateTime.now());
                administratorRepository.save(admin);
                return Optional.of(admin);
            }
        }
        
        return Optional.empty();
    }
    
    /**
     * Create new administrator
     * @param username the username
     * @param password the plain text password
     * @param fullName the full name
     * @param email the email
     * @return created Administrator
     * @throws IllegalArgumentException if username already exists
     */
    public Administrator createAdministrator(String username, String password, String fullName, String email) {
        if (administratorRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
        
        if (email != null && !email.isEmpty() && administratorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
        
        String hashedPassword = PasswordUtility.hashPassword(password);
        Administrator administrator = new Administrator(username, hashedPassword, fullName, email);
        
        return administratorRepository.save(administrator);
    }
    
    /**
     * Get administrator by username
     * @param username the username
     * @return Optional containing Administrator
     */
    @Transactional(readOnly = true)
    public Optional<Administrator> getAdministratorByUsername(String username) {
        return administratorRepository.findByUsername(username);
    }
    
    /**
     * Get all active administrators
     * @return list of active administrators
     */
    @Transactional(readOnly = true)
    public List<Administrator> getAllActiveAdministrators() {
        return administratorRepository.findByIsActiveTrue();
    }
    
    /**
     * Update administrator password
     * @param username the username
     * @param newPassword the new plain text password
     * @return true if password updated successfully
     */
    public boolean updateAdministratorPassword(String username, String newPassword) {
        Optional<Administrator> adminOptional = administratorRepository.findByUsername(username);
        
        if (adminOptional.isPresent()) {
            Administrator admin = adminOptional.get();
            admin.setPasswordHash(PasswordUtility.hashPassword(newPassword));
            administratorRepository.save(admin);
            return true;
        }
        
        return false;
    }
    
    /**
     * Deactivate administrator
     * @param username the username
     * @return true if administrator deactivated successfully
     */
    public boolean deactivateAdministrator(String username) {
        Optional<Administrator> adminOptional = administratorRepository.findByUsername(username);
        
        if (adminOptional.isPresent()) {
            Administrator admin = adminOptional.get();
            admin.setIsActive(false);
            administratorRepository.save(admin);
            return true;
        }
        
        return false;
    }
    
    /**
     * Get administrator count
     * @return total number of administrators
     */
    @Transactional(readOnly = true)
    public long getAdministratorCount() {
        return administratorRepository.count();
    }
    
    /**
     * Get active administrator count
     * @return number of active administrators
     */
    @Transactional(readOnly = true)
    public long getActiveAdministratorCount() {
        return administratorRepository.countByIsActiveTrue();
    }
}
