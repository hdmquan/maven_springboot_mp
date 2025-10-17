// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Password utility for secure password operations using BCrypt
 * BIT235 Assessment 2 - Part 1
 */
@Component
public class PasswordUtility {
    
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    
    /**
     * Hash plain text password using BCrypt
     * @param plainPassword the plain text password
     * @return hashed password string
     * @throws IllegalArgumentException if password is null or empty
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return PASSWORD_ENCODER.encode(plainPassword);
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
        return PASSWORD_ENCODER.matches(plainPassword, hashedPassword);
    }
    
    /**
     * Get password encoder instance
     * @return BCryptPasswordEncoder instance
     */
    public static PasswordEncoder getPasswordEncoder() {
        return PASSWORD_ENCODER;
    }
    
    /**
     * Check password strength
     * @param password the password to check
     * @return true if password meets strength requirements
     */
    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]{}|;:,.<>?".indexOf(ch) >= 0);
        
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}
