package com.example.security.service;

import com.example.security.model.UserPrincipal;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Username : " + username);

        // Username will be searched in the db
        User user = repository.findByUsername(username);

        System.out.println("User : " + user);

//        if(user == null) {
//             throw new UsernameNotFoundException("User not recognized 404");
//        }

        return new UserPrincipal(user);
    }
}
