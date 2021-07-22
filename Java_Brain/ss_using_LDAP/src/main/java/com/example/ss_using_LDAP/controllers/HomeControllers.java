package com.example.ss_using_LDAP.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControllers {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome to all</h1>");
    }
}