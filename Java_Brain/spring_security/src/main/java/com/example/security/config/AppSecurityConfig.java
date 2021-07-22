package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// First this file will be checked as it contains all the configurations (@Configuration).
// Using AppSecurityConfig class and all it's configurations we can have our own username and password.
@Configuration
@EnableWebSecurity  // By adding @EnableWebSecurity, we get Spring Security and MVC integration support:
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    // By seeing this configure() method spring security will avoid doing the default configuration (creating its own user and password
    // and creating a single user)
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        // Set my configuration on the auth object (first tell what type of authentication you want). Here we are creating a list of
        // users with their own specific role.
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("aa")
                .password("aa")
                .roles("USER")      // You can give any name to roles like "store_clerk".
                .and()  // returns an object that is of same state as inMemoryAuthentication() returns
                .withUser("gun")
                .password("gun")
                .roles("ADMIN");    // You can give any name to roles like "store_manager".

    }

    // How to set a password encoder? Create a @Bean of type PasswordEncoder and expose it to Spring Security and Spring will
    // look for the bean of type PasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder() {

        // return any password encoder but to keep to simple we choose not to encode password. It is deprecated not because it will
        // go away in future but as a warning to developers it is not sthng you should be using.
        return NoOpPasswordEncoder.getInstance();
    }

    // We can use HttpSecurity to configure the access restriction of endpoints. For example "/admin" can only be accessed by admins.
    // To get hold of HttpSecurity object is very similar to Authentication (AuthenticationManagerBuilder) object.

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // It is where you specify the mapping of path to role. Specify the path using method antMatchers() which lets you configure
        // what the path should be using wildcards. For example "/**" matches all paths ("**" indicates all paths in the current level
        // and any nested level below this).
        // hasRole() can configure a single role only. For multiple roles use hasAnyRole().

        // Ordering of roles matter. So go from most restricted role to least restricted role. For example here go from ADMIN
        // (most restrictive) to unauthorized user (least restrictive). Spring Security doesn't really know that ADMIN is of higher role
        // than USER as both are just strings for Spring Security. So we need to tell Spring Security that ADMIN is having higher privilege
        // than USER. It can done by using hasAnyRole() to "/user" and permits both USER and ADMIN to access this endpoint.
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();

//        http.authorizeRequests()
//                // These are the files we want to allow to the users (only for "/") whether the user is logged in or not.
//                // permitAll() will allow any type of access (logged in or not)
//                .antMatchers("/", "static/css", "static/js").permitAll()
//                .antMatchers("/**").hasRole("ADMIN")
//                .and()
//                .formLogin();

    }
}