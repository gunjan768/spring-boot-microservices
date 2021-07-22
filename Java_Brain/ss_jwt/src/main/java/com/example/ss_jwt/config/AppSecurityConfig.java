package com.example.ss_jwt.config;

import com.example.ss_jwt.filters.JwtRequestFilter;
import com.example.ss_jwt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// First this file will be checked as it contains all the configurations (@Configuration).
// Using AppSecurityConfig class and all it's configurations we can have our own username and password.
@Configuration
@EnableWebSecurity  // By adding @EnableWebSecurity, we get Spring Security and MVC integration support:
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myUserDetailsService")
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // By seeing this configure() method spring security will avoid doing the default configuration (creating its own user and password
    // and creating a single user)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
    }

    // How to set a password encoder? Create a @Bean of type PasswordEncoder and expose it to Spring Security and Spring will
    // look for the bean of type PasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder() {

        // return any password encoder but to keep to simple we choose not to encode password. It is deprecated not because it will
        // go away in future but as a warning to developers it is not sthng you should be using.
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()    // any other request has to be authenticated
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);   // As we are using JWT so we don't want the SS to manage a session

        // SS is not creating a session so there should be something that works for each request and set up security context for each
        // time. So we will use JwtRequestFilter class (see JwtRequestFilter.java)
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}