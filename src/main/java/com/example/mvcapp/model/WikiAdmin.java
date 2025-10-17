// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * WikiAdmin entity for admin users
 * BIT235 Assessment 2 - Part 1
 */
@Entity
@Table(name = "wiki_admins")
public class WikiAdmin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Username field - unique identifier
    private String username;
    
    // Password field - stored as hashed password
    private String password;
    
    // Optional admin info fields
    private String fullName;
    private String email;
    
    // Default constructor (required by JPA)
    public WikiAdmin() {}
    
    // Constructor with essential fields
    public WikiAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Constructor with all fields
    public WikiAdmin(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "WikiAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}'; // Password excluded for security
    }
}
