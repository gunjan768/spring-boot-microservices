package com.example.spring_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// How to enable Automatic Reloading i.e. 'spring-boot-devtools'
// https://medium.com/@bhanuchaddha/spring-boot-devtools-on-intellij-c0ab3f9afa63

@RestController
public class RestControllerExample {

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return "Coach : " + coachName + ", Team Name : " + teamName;
    }

    @GetMapping("/workout")
    public String getWorkout() {
        return "Run a hard 5k";
    }

    @GetMapping("/workouts")
    public String getWorkouts() {
        return "Run a hard 5ks";
    }
}