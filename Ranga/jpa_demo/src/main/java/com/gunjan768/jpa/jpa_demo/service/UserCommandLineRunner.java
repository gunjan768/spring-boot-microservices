package com.gunjan768.jpa.jpa_demo.service;

import com.gunjan768.jpa.jpa_demo.model.User;
import com.gunjan768.jpa.jpa_demo.repository.UserDaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// We are using H2 database which is an internal (in memory) database created inside the app, the schemas are defined, tables are created, data
// is populated and once the app stops, the entire db is destroyed and removed from the memory i.e. data is not persisted b/w restarts.
// Hibernate is coming from Spring Data Jpa Starter and datasource is created through Spring Boot Auto Configuration

// CommandLineRunner is used to insert some data when app starts. It is typically executed when the context launches up.
@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserDaoRepository userDaoRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Gunjan", "Admin");
        long id = userDaoRepository.insert(user);

        log.info("New User is created : " + user);
    }
}
