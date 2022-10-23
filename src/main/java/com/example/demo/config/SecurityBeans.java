package com.example.demo.config;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.ICredentialRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GoogleAuthenticator googleAuthenticator(ICredentialRepository credentialRepository) {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticator.setCredentialRepository(credentialRepository);
        return googleAuthenticator;
    }

    @Bean
    public AuthenticationTrustResolver authenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
