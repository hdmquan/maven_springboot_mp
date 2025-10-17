// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for Wiki Administrator system
 * BIT235 Assessment 2 - Part 1
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    /**
     * Configure password encoder bean for BCrypt hashing
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configure security filter chain
     * @param http HttpSecurity configuration
     * @return SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for simplicity (in production, CSRF should be enabled)
            .csrf(csrf -> csrf.disable())
            
            // Configure authorization rules
            .authorizeHttpRequests(authz -> authz
                // Allow access to all pages for simplicity
                .anyRequest().permitAll()
            );
        
        return http.build();
    }
}
