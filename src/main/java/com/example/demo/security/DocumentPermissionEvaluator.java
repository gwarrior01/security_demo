package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("documentPermissionEvaluator")
public class DocumentPermissionEvaluator {

    public boolean check(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return name.equals(authentication.getName()) ||
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals("ROLE_ADMIN"));
    }
}
