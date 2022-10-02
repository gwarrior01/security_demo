package com.example.demo.config;

import com.example.demo.security.AuthProviderImpl;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/login", "/auth/registration", "/error", "/auth/2fa").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .failureForwardUrl("/auth/login?error")
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GoogleAuthenticator googleAuthenticator(ICredentialRepository credentialRepository) {
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder builder
                = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder();
        builder
                .setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(60))
                .setWindowSize(10)
                .setNumberOfScratchCodes(0);

        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(builder.build());
        googleAuthenticator.setCredentialRepository(credentialRepository);
        return googleAuthenticator;
    }
}
