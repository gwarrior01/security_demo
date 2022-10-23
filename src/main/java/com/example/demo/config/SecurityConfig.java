package com.example.demo.config;

import com.example.demo.security.AuthProviderImpl;
import com.example.demo.security.Google2faFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;
    private final Google2faFilter google2faFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/login", "/registration").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(google2faFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/");
    }
}
