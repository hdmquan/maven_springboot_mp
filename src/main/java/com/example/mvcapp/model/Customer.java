// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.model;

import com.example.mvcapp.util.EncryptionConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Convert(converter = EncryptionConverter.class)
    private String email; // Will be stored encrypted in DB
    
    @Convert(converter = EncryptionConverter.class)
    private String password; // Will be stored encrypted in DB
    
    // Default constructor (required by JPA)
    public Customer() {}
    
    // Constructor with parameters
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}'; // Note: password not included in toString for security
    }
}
