package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Since spring 2, we were writing our bean configurations to xml files. But Spring 3 gave the freedom to move bean definitions out of xml files.
// we can give bean definitions in Java files itself. This is called Spring Java Config feature (using @Configuration annotation). Java-based
// configuration option enables you to write most of your Spring configuration without XML but with the help of few Java-based annotations.
// Annotating a class with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.

// Use @Configuration annotation on top of any class to declare that this class provides one or more @Bean methods and may be processed by
// the Spring container to generate bean definitions and service requests for those beans at runtime.
@Configuration
public class StudentConfig {

    // The objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is
    // an object that is instantiated, assembled, and otherwise managed by a Spring IoC container. These beans are created with the
    // configuration metadata that you supply to the container.

    // Spring @Bean annotation tells that a method produces a bean to be managed by the Spring container. It is a method-level annotation.
    // During Java configuration (@Configuration), the method is executed and its return value is registered as a bean within a BeanFactory.

    // CommandLineRunner is a functional interface (annotated with @FunctionalInterface to mark the interface functional). So returning a
    // function with same signature (same to the abstract method of CommandLineRunner interface) satisfies the return type of below method.

    // CommandLineRunner will automatically get executed on application startup (when this app starts for the first time).
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        // Returning a whole function which accepts one argument (ellipse see CommandLineRunner interface) and whose return type is void.
        return args -> {
            Student mariam = new Student("Mariam", "m@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
            Student alex = new Student("Alex", "a@gmail.com", LocalDate.of(2010, Month.JANUARY, 5));

            // Hibernate will run when we call saveAll() method.
            repository.saveAll(List.of(mariam, alex));
        };
    }
}