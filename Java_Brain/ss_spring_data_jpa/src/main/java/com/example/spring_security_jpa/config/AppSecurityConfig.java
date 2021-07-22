package com.example.spring_security_jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

// First this file will be checked as it contains all the configurations (@Configuration).
// Using AppSecurityConfig class and all it's configurations we can have our own username and password.
@Configuration
@EnableWebSecurity  // By adding @EnableWebSecurity, we get Spring Security and MVC integration support:
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    // By seeing this configure() method spring security will avoid doing the default configuration (creating its own user and password
    // and creating a single user)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // For JPA there is not out of box implementation as we have for JDBC and inMemoryAuthentication(). For SS to work with JPA
        // we need to create an instance of UserDetailsService (class which implements it).
        auth.userDetailsService(userDetailsService);
    }

    // This configure(HttpSecurity http) is for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();
    }

    // How to set a password encoder? Create a @Bean of type PasswordEncoder and expose it to Spring Security and Spring will
    // look for the bean of type PasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder() {

        // return any password encoder but to keep it simple we choose not to encode password. It is deprecated not because it will
        // go away in future but as a warning to developers it is not sthng you should be using.
        return NoOpPasswordEncoder.getInstance();
    }
}