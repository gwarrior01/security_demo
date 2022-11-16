package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .authorizeRequests()
//                .mvcMatchers("/a/**").access("hasRole('ADMIN') and authentication.name=='john'")
//                .and()
//                .authorizeRequests()
//                .mvcMatchers("/a/**").access("@permissionEvaluator.check()")
//                .and()
                .authorizeHttpRequests(auth -> {
                            auth.mvcMatchers("/a").authenticated();
                            auth.anyRequest().permitAll();

//                            auth.antMatchers("/a").authenticated();
//                            auth.anyRequest().permitAll();

//                            auth.mvcMatchers("/a/**").hasAnyRole("ADMIN");
//                            auth.mvcMatchers("/b/**").hasAnyRole("USER");
//                            auth.anyRequest().permitAll();


//                            auth.mvcMatchers("/a/**").hasAuthority("write");
//                            auth.mvcMatchers("/b/**").hasAnyAuthority("read", "write");
//                            auth.anyRequest().permitAll();

//                            auth.mvcMatchers("/c/{rus}").permitAll();
//                            auth.anyRequest().denyAll();

//                            auth.regexMatchers("rus").permitAll();
//                            auth.anyRequest().denyAll();

                        }


                )
                .build();
    }

    @Bean
    public UserDetailsService userDetails(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

        UserDetails u1 = User.builder()
                .username("john")
                .password(passwordEncoder.encode("12345"))
//                .authorities("write")
                .roles("ADMIN")
                .build();


        UserDetails u2 = User.builder()
                .username("bill")
                .password(passwordEncoder.encode("12345"))
//                .authorities("read")
                .roles("USER")
                .build();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public RoleHierarchy roleHierarchy() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
//        return roleHierarchy;
//    }
}
