// Alan Huynh | s1557984 | alan@protoflow.com.au

package com.example.mvcapp.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Data
    @AllArgsConstructor
    static class Addition {
        private int num1;
        private int num2;
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("helloMsg", "BIT235 Spring Boot + Thymeleaf tutorial");
        return "hello"; // resolves to hello.html in templates/
    }

    @GetMapping("/calc")
    @ResponseBody
    public String calc(int num1, int num2) {
        return String.valueOf(num1 + num2);
    }
    
    /**
     * Redirect root path to login page
     * @return redirect to login page
     */
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
