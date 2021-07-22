package com.example.spring_security_jpa.service;

import com.example.spring_security_jpa.models.MyUserDetails;
import com.example.spring_security_jpa.models.User;
import com.example.spring_security_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        final Optional<User> user = userRepository.findByUserName(s);

//        if(user.isEmpty()) {
//            throw new UsernameNotFoundException("Check your credentials");
//        }

        user.orElseThrow(() -> new UsernameNotFoundException("Check your credentials"));

//        return user.map(user1 -> new MyUserDetails(user1)).get();
        return user.map(MyUserDetails::new).get();
    }
}
