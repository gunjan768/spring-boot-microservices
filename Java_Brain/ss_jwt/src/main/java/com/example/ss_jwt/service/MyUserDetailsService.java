package com.example.ss_jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // You can create your own User class or use the default User class provided by SS which takes username, password and authorities. As
        // we are not dealing with authorities so we passed an empty ArrayList.
        return new User("gun", "pass", new ArrayList<>());
    }
}
