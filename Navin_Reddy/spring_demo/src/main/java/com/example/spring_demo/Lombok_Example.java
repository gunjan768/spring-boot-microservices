package com.example.spring_demo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Slf4j  // For logging
//@Data  // Generate everything
public class Lombok_Example {

    @Getter
    @Setter
    private int age;
    private String name;
    private float marks;

    void getAny() {
        log.info("getAny is called");
    }
}
