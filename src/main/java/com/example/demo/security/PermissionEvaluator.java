package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("permissionEvaluator")
public class PermissionEvaluator {

    public boolean check() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName().equals("john") &&
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()).contains("ROLE_ADMIN");
    }
}
