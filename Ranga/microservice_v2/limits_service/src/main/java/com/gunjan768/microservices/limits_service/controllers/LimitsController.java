package com.gunjan768.microservices.limits_service.controllers;

import com.gunjan768.microservices.limits_service.configuration.LimitsConfiguration;
import com.gunjan768.microservices.limits_service.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsConfiguration limitsConfiguration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        System.out.println("Average : " + limitsConfiguration.getAverage());
        return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
    }
}
