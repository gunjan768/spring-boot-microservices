package com.example.security.model;

import com.example.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// UserPrincipal simply means current user.
public class UserPrincipal implements UserDetails {

    private final User user;

    public UserPrincipal(User user) {
        super();
        this.user = user;
    }

    // Return the collection of authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return "$2y$12$wsH6Z/7JAeorviMz7o2maOXogQncjhFnTwG9NE8Mr/W5/66shCLUW";
    }

    @Override
    public String getUsername() {
        return "user.getUsername()";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
