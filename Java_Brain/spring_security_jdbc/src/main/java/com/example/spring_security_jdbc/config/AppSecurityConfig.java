package com.example.spring_security_jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

// First this file will be checked as it contains all the configurations (@Configuration).
// Using AppSecurityConfig class and all it's configurations we can have our own username and password.
@Configuration
@EnableWebSecurity  // By adding @EnableWebSecurity, we get Spring Security and MVC integration support:
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    // DataSource tells SS that we want to access DB (internal or external both db). By default H2 is the default db. But we can change the
    // default db by using application.properties by assigning values to spring.datasource.url, spring.datasource.username and
    // spring.datasource.password.
    @Autowired
    DataSource dataSource;

    // By seeing this configure() method spring security will avoid doing the default configuration (creating its own user and password
    // and creating a single user)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Previously we did the inMemoryAuthentication() and now jdbcAuthentication(). As we have used an embedded db (H2) then SB
        // is smart enough to create the DataSource. We are creating our own user and SS can do that if our H2 db is clean (if there is
        // no data).

        // Here if DataSource points to MySql then SS will do all the stuff for MySql but here our db is H2. At the runtime when
        // authentication happens, SS is using this DataSource to verify user information against what was already in the db and in this
        // case we are populating the data (user and password).
//        auth.jdbcAuthentication()
//                // We have actually configured SS to point to your H2 db
//                .dataSource(dataSource)
//                .withDefaultSchema()    // To see the structure of DefaultSchema see schema.sql
//                .withUser(
//                        User.withUsername("user")
//                        .password("pass")
//                        .roles("USER")
//                )
//                .withUser(
//                        User.withUsername("admin")
//                                .password("pass")
//                                .roles("ADMIN")
//                );

        // ***************************************************************************************************************************

        // Now we are not using the default schema. We have created our own schema (table) and populated data in it (see schema.sql and
        // data.sql) but exactly same as default one. SS will not not use the defaultSchema and use our schema. Here SS will use our
        // schema but it will expect the same to that of default.
//        auth.jdbcAuthentication()
//                // We have actually configured SS to point to your H2 db
//                .dataSource(dataSource);

        // ***************************************************************************************************************************

        // ***************************************************************************************************************************

        // Now we are using our own schema and it is different from the default one. We need to tell SS that don't expect the default schema.
        // We can do this using usersByUsernameQuery() and authoritiesByUsernameQuery(). We need to tell the SS which table to query
        // and how to query. Here we are flexible enough. We can change the table name, or can add or remove any column which we don't
        // want. We are flexible to structure our own schema.
        auth.jdbcAuthentication()
                // We have actually configured SS to point to your H2 db
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?");

        // ***************************************************************************************************************************
    }

    // This configure (HttpSecurity http) is for authorization
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

        // return any password encoder but to keep to simple we choose not to encode password. It is deprecated not because it will
        // go away in future but as a warning to developers it is not sthng you should be using.
        return NoOpPasswordEncoder.getInstance();
    }
}