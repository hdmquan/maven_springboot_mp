// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password utility for BCrypt hashing
 * BIT235 Assessment 2 - Part 1
 */
public class PasswordUtil {
    
    // BCrypt password encoder instance
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    /**
     * Hash plain text password using BCrypt
     * @param plainPassword the plain text password
     * @return hashed password string
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return passwordEncoder.encode(plainPassword);
    }
    
    /**
     * Verify if plain text password matches hashed password
     * @param plainPassword the plain text password
     * @param hashedPassword the stored hashed password
     * @return true if passwords match
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
