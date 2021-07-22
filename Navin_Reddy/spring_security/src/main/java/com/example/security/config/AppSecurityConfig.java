package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

// First this file will be checked as it contains all the configurations (@Configuration).
// Using AppSecurityConfig class and all it's configurations we can have our own username and password.
@Configuration
@EnableWebSecurity  // By adding @EnableWebSecurity, we get Spring Security and MVC integration support:
//@EnableOAuth2Sso
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    // How UserDetailsService (UserDetailsService) reference variable will know that it has connect DAO layer ? So we need to create a class
    // which will implement this UserDetailsService interface.

    // Service class
    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    // We can have our own username and password. But we mainly want the username and password from the database
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//
//        // UserDetails is an inbuilt interface in Spring
//        List<UserDetails> users = new ArrayList<>();
//
//        // withDefaultPasswordEncoder() is not advised to use but for time being lets use it plus it is deprecated.
//        users.add(User.withDefaultPasswordEncoder()
//                .username("gunjan768")
//                .password("1234")
//                .roles("USER")      // authorization
//                .build()
//        );
//
//        return new InMemoryUserDetailsManager(users);
//    }

    // Spring Security provides a variety of options for performing authentication. These follow a simple contract – an Authentication
    // request is processed by an AuthenticationProvider and a fully authenticated object with full credentials is returned. The standard and
    // most common implementation is the DaoAuthenticationProvider – which retrieves the user details from a simple, read-only user DAO –
    // the UserDetailsService. This User Details Service only has access to the username in order to retrieve the full user entity. This is
    // enough for most scenarios.

    // Spring @Bean Annotation is applied on a method to specify that it returns a bean to be managed by Spring context. Spring Bean annotation
    // is usually declared in Configuration classes methods. In this case, bean methods may reference other @Bean methods in the same class by
    // calling them directly.
    
    // Here we are fetching data from the db (above commented method is not fetching the data from the db). An AuthenticationProvider
    // implementation that retrieves user details from a UserDetailsService.
    @Bean
    public AuthenticationProvider authenticationProvider() {

        System.out.println("Inside authenticationProvider()");

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        // Controller talks to Service and Service talks to DAO, in the same way, Configure talks to Service and Service talks to DAO.
        // UserDetailsService class is responsible for fetching data from the db.
        authenticationProvider.setUserDetailsService(userDetailsService);

        // For time being lets use NoOpPasswordEncoder (not encode the password, password will be in simple text format) but later we will
        // use hashing technique
        // authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }

    // By default Spring will has its own login form (localhost:8080/login). We have to inform Spring framework that we have our own
    // login page and don't want to use the default one. We can tell using configure() method. Note that the order of the antMatchers()
    // elements is significant – the more specific rules need to come first, followed by the more general ones.Note that the order of the
    // antMatchers() elements is significant – the more specific rules need to come first, followed by the more general ones.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("Inside configure()");

        // The formLogin().permitAll() method allows granting access to all users for all URLs associated with form based log in.
        // The default URL where the Spring Login will POST to trigger the authentication process is /login (POST request). But we
        // change it using loginProcessingUrl() method.
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/perform_login")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)      // Once you are logged out we need to clear everything
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout_success").permitAll();      // After logout where you want to move
    }

    // We are using OAuth2. Comment the above method (same method).
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        super.configure(http);
//
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();   // After logout where you want to move
//
//    }
}