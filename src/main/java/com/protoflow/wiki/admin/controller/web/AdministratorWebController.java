// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.controller.web;

import com.protoflow.wiki.admin.model.Administrator;
import com.protoflow.wiki.admin.service.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Web controller for Administrator authentication and management
 * BIT235 Assessment 2 - Part 1
 */
@Controller
@RequestMapping("/admin")
public class AdministratorWebController {
    
    private final AdministratorService administratorService;
    
    @Autowired
    public AdministratorWebController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
    
    /**
     * Display login page
     * @return login template
     */
    @GetMapping("/login")
    public String displayLoginPage() {
        return "admin/login";
    }
    
    /**
     * Process login form submission
     * @param username from form
     * @param password from form
     * @param model for view data
     * @param redirectAttributes for flash messages
     * @return redirect to dashboard or login page
     */
    @PostMapping("/login")
    public String processLoginSubmission(@RequestParam String username, 
                                       @RequestParam String password,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {
        
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Username and password are required");
            return "admin/login";
        }
        
        try {
            // Authenticate administrator
            Optional<Administrator> adminOptional = administratorService.authenticateAdministrator(username.trim(), password);
            
            if (adminOptional.isPresent()) {
                Administrator admin = adminOptional.get();
                redirectAttributes.addFlashAttribute("welcomeMessage", 
                    "Welcome, " + (admin.getFullName() != null ? admin.getFullName() : admin.getUsername()) + "! You have successfully logged in.");
                redirectAttributes.addFlashAttribute("administrator", admin);
                return "redirect:/admin/dashboard";
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "admin/login";
            }
            
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during login. Please try again.");
            return "admin/login";
        }
    }
    
    /**
     * Display registration page
     * @return register template
     */
    @GetMapping("/register")
    public String displayRegistrationPage() {
        return "admin/register";
    }
    
    /**
     * Process registration form submission
     * @param username from form
     * @param password from form
     * @param confirmPassword password confirmation
     * @param fullName from form
     * @param email from form
     * @param model for view data
     * @param redirectAttributes for flash messages
     * @return redirect to login or register page
     */
    @PostMapping("/register")
    public String processRegistrationSubmission(@RequestParam String username,
                                              @RequestParam String password,
                                              @RequestParam String confirmPassword,
                                              @RequestParam(required = false) String fullName,
                                              @RequestParam(required = false) String email,
                                              Model model,
                                              RedirectAttributes redirectAttributes) {
        
        // Validate input
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Username is required");
            return "admin/register";
        }
        
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Password is required");
            return "admin/register";
        }
        
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "admin/register";
        }
        
        if (password.length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters long");
            return "admin/register";
        }
        
        try {
            // Create new administrator
            Administrator newAdmin = administratorService.createAdministrator(
                username.trim(), password, fullName, email);
            
            redirectAttributes.addFlashAttribute("successMessage", 
                "Registration successful! Please login with your credentials.");
            return "redirect:/admin/login";
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/register";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            return "admin/register";
        }
    }
    
    /**
     * Display administrator dashboard
     * @param model for view data
     * @return dashboard template
     */
    @GetMapping("/dashboard")
    public String displayAdministratorDashboard(Model model) {
        return "admin/dashboard";
    }
    
    /**
     * Process logout
     * @param redirectAttributes for flash messages
     * @return redirect to login page
     */
    @PostMapping("/logout")
    public String processLogout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been successfully logged out.");
        return "redirect:/admin/login";
    }
}
