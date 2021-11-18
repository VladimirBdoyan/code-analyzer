package com.example.security.security;

import com.example.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUserDetails implements UserDetails {

    private User user;

    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public AppUserDetails(User user) {
        this.user = user;
    }

    public AppUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(a -> new SimpleGrantedAuthority(a.getAuthorityID().getAuthority()))
                .collect(Collectors.toSet());

        //Get role and add it into a set
        GrantedAuthority role = new SimpleGrantedAuthority(this.user.getRole().name());
        authorities.add(role);

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user == null ? username : user.getUsername();
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
        return user.isEnabled();
    }
}
