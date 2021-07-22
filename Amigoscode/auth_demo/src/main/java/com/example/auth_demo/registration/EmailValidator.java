package com.example.auth_demo.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // Todo: Regex to validate email and for now we have returned true
        return true;
    }
}
