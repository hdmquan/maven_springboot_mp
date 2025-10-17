// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.controller;

import com.example.mvcapp.model.WikiAdmin;
import com.example.mvcapp.repository.WikiAdminRepository;
import com.example.mvcapp.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controller for handling Wiki Administrator login and registration
 * Created for BIT235 Assessment 2 - Part 1
 * Student: [Your Name] | Student ID: [Your Student ID]
 */
@Controller
public class LoginController {
    
    @Autowired
    private WikiAdminRepository adminRepository;
    
    /**
     * Display the login page
     * @return login template name
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    /**
     * Handle login form submission
     * @param username the username from the form
     * @param password the password from the form
     * @param model the model to pass data to the view
     * @param redirectAttributes for flash messages
     * @return redirect to welcome page on success, login page on failure
     */
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                              @RequestParam String password,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        // Validate input parameters
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Username and password are required");
            return "login";
        }
        
        try {
            // Find admin by username
            Optional<WikiAdmin> adminOptional = adminRepository.findByUsername(username.trim());
            
            if (adminOptional.isPresent()) {
                WikiAdmin admin = adminOptional.get();
                
                // Verify password using BCrypt
                if (PasswordUtil.verifyPassword(password, admin.getPassword())) {
                    // Login successful - redirect to admin console
                    redirectAttributes.addFlashAttribute("welcomeMessage", 
                        "Welcome, " + admin.getFullName() + "! You have successfully logged in.");
                    redirectAttributes.addFlashAttribute("admin", admin);
                    return "redirect:/admin/welcome";
                } else {
                    // Invalid password
                    model.addAttribute("error", "Invalid username or password");
                    return "login";
                }
            } else {
                // Username not found
                model.addAttribute("error", "Invalid username or password");
                return "login";
            }
            
        } catch (Exception e) {
            // Handle any unexpected errors
            model.addAttribute("error", "An error occurred during login. Please try again.");
            return "login";
        }
    }
    
    /**
     * Display the registration page
     * @return register template name
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    
    /**
     * Handle registration form submission
     * @param username the username from the form
     * @param password the password from the form
     * @param confirmPassword password confirmation
     * @param fullName the full name from the form
     * @param email the email from the form
     * @param model the model to pass data to the view
     * @param redirectAttributes for flash messages
     * @return redirect to login page on success, register page on failure
     */
    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String confirmPassword,
                                    @RequestParam(required = false) String fullName,
                                    @RequestParam(required = false) String email,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        
        // Validate input parameters
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Username is required");
            return "register";
        }
        
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Password is required");
            return "register";
        }
        
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }
        
        if (password.length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters long");
            return "register";
        }
        
        try {
            // Check if username already exists
            if (adminRepository.existsByUsername(username.trim())) {
                model.addAttribute("error", "Username already exists. Please choose a different username.");
                return "register";
            }
            
            // Hash the password using BCrypt
            String hashedPassword = PasswordUtil.hashPassword(password);
            
            // Create new admin user
            WikiAdmin newAdmin = new WikiAdmin(username.trim(), hashedPassword, fullName, email);
            adminRepository.save(newAdmin);
            
            // Registration successful
            redirectAttributes.addFlashAttribute("successMessage", 
                "Registration successful! Please login with your credentials.");
            return "redirect:/login";
            
        } catch (Exception e) {
            // Handle any unexpected errors
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            return "register";
        }
    }
    
    /**
     * Display the admin welcome page
     * @param model the model to pass data to the view
     * @return welcome template name
     */
    @GetMapping("/admin/welcome")
    public String showWelcomePage(Model model) {
        return "welcome";
    }
}
