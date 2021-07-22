package com.gunjan768.jpa.jpa_demo.service;

import com.gunjan768.jpa.jpa_demo.model.User;
import com.gunjan768.jpa.jpa_demo.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class UserJpaCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserJpaCommandLineRunner.class);

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Mallika", "Admin");
        userJpaRepository.save(user);

        log.info("New User is created : " + user);

        List<User> all = userJpaRepository.findAll();
        userJpaRepository.findById(1L);
    }
}