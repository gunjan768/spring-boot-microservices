package com.example.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home.jsp";
    }

    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("Inside loginPage()");

        return "login.jsp";
    }

    @RequestMapping("/logout_success")
    public String logoutSuccess() {
        return "logout.jsp";
    }

    // Finding it's use case ???
    @RequestMapping("user")
    @ResponseBody       // Want in XML or JSON format and not the page (view)
    public Principal user(Principal principal) {
        System.out.println("Inside user()");

        return principal;
    }
}