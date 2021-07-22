package com.example.spring_security_jdbc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControllers {

    // Accessed by anybody
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome to all</h1>");
    }

    // Should only be accessed by logged in user (which also includes admin)
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome user</h1>");
    }

    // Should only be accessed by admin
    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome admin</h1>");
    }
}