// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.config;

import com.protoflow.wiki.admin.model.Administrator;
import com.protoflow.wiki.admin.repository.AdministratorRepository;
import com.protoflow.wiki.admin.util.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Data initialization component to populate database with default administrators
 * BIT235 Assessment 2 - Part 1
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    private final AdministratorRepository administratorRepository;
    
    @Autowired
    public DatabaseInitializer(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Always check and create default administrators (useful for Docker restarts)
        long administratorCount = administratorRepository.count();
        System.out.println("Current administrators in database: " + administratorCount);
        
        if (administratorCount == 0) {
            System.out.println("Initializing database with default administrators...");
            
            // Create default administrators with secure passwords
            createDefaultAdministrator("admin", "AdminPass123!", "System Administrator", "admin@wiki.com");
            createDefaultAdministrator("john", "JohnPass123!", "John Smith", "john.smith@wiki.com");
            createDefaultAdministrator("sarah", "SarahPass123!", "Sarah Johnson", "sarah.johnson@wiki.com");
            createDefaultAdministrator("mike", "MikePass123!", "Mike Wilson", "mike.wilson@wiki.com");
            
            System.out.println("Default administrators created successfully!");
        }
        
        System.out.println("Available test accounts:");
        System.out.println("Username: admin, Password: AdminPass123!");
        System.out.println("Username: john, Password: JohnPass123!");
        System.out.println("Username: sarah, Password: SarahPass123!");
        System.out.println("Username: mike, Password: MikePass123!");
        System.out.println("Application ready! Access at: http://localhost:8080/admin/login");
    }
    
    /**
     * Create default administrator with hashed password
     * @param username for administrator
     * @param password plain text password (will be hashed)
     * @param fullName administrator full name
     * @param email administrator email
     */
    private void createDefaultAdministrator(String username, String password, String fullName, String email) {
        try {
            // Hash password using BCrypt
            String hashedPassword = PasswordUtility.hashPassword(password);
            
            // Create new administrator
            Administrator administrator = new Administrator(username, hashedPassword, fullName, email);
            
            // Save to database
            administratorRepository.save(administrator);
            
            System.out.println("Created administrator: " + username);
            
        } catch (Exception e) {
            System.err.println("Error creating administrator " + username + ": " + e.getMessage());
        }
    }
}
