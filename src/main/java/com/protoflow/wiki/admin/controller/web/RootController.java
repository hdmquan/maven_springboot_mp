// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.protoflow.wiki.admin.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Root controller for application entry points
 * BIT235 Assessment 2 - Part 1
 */
@Controller
public class RootController {
    
    /**
     * Redirect root path to admin login page
     * @return redirect to admin login page
     */
    @GetMapping("/")
    public String redirectToAdminLogin() {
        return "redirect:/admin/login";
    }
}
