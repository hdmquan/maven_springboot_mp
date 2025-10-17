// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.config;

import com.example.mvcapp.model.WikiAdmin;
import com.example.mvcapp.repository.WikiAdminRepository;
import com.example.mvcapp.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataLoader class to populate the database with test admin users
 * Created for BIT235 Assessment 2 - Part 1
 * Student: [Your Name] | Student ID: [Your Student ID]
 */
@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private WikiAdminRepository adminRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Always check and create admin users (useful for Docker restarts)
        long userCount = adminRepository.count();
        System.out.println("Current admin users in database: " + userCount);
        
        if (userCount == 0) {
            System.out.println("Loading test admin users into database...");
            
            // Create test admin users with secure passwords
            createTestAdmin("admin", "admin123", "System Administrator", "admin@wiki.com");
            createTestAdmin("john", "password123", "John Smith", "john.smith@wiki.com");
            createTestAdmin("sarah", "securepass456", "Sarah Johnson", "sarah.johnson@wiki.com");
            createTestAdmin("mike", "mypassword789", "Mike Wilson", "mike.wilson@wiki.com");
            
            System.out.println("Test admin users loaded successfully!");
        }
        
        System.out.println("Available test accounts:");
        System.out.println("Username: admin, Password: admin123");
        System.out.println("Username: john, Password: password123");
        System.out.println("Username: sarah, Password: securepass456");
        System.out.println("Username: mike, Password: mypassword789");
        System.out.println("Application ready! Access at: http://localhost:8080");
    }
    
    /**
     * Create a test admin user with hashed password
     * @param username the username for the admin
     * @param password the plain text password (will be hashed)
     * @param fullName the full name of the admin
     * @param email the email address of the admin
     */
    private void createTestAdmin(String username, String password, String fullName, String email) {
        try {
            // Hash the password using BCrypt
            String hashedPassword = PasswordUtil.hashPassword(password);
            
            // Create new admin user
            WikiAdmin admin = new WikiAdmin(username, hashedPassword, fullName, email);
            
            // Save to database
            adminRepository.save(admin);
            
            System.out.println("Created admin user: " + username);
            
        } catch (Exception e) {
            System.err.println("Error creating admin user " + username + ": " + e.getMessage());
        }
    }
}
