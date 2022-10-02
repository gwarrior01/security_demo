package com.example.demo.security;

import com.example.demo.entity.Google2FaCompatible;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TwoFaUserDetails implements UserDetails, Google2FaCompatible {

    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public TwoFaUserDetails(User user) {
        this.user = user;
        this.authorities = mapRolesToAuthorities(user.getRoles());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    @Override
    public Boolean getUseGoogle2Fa() {
        return this.user.getUseGoogle2Fa();
    }

    @Override
    public String getGoogle2FaSecret() {
        return this.user.getGoogle2FaSecret();
    }
}
