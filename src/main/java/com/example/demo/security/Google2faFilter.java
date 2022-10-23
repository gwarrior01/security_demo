package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class Google2faFilter extends OncePerRequestFilter {

    private final AuthenticationTrustResolver authenticationTrustResolver;
    private final Google2faFailureHandler google2faFailureHandler;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !authenticationTrustResolver.isAnonymous(authentication)) {
            log.debug("Processing 2FA Filter");

            if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof TwoFaUserDetails) {
                TwoFaUserDetails user = (TwoFaUserDetails) authentication.getPrincipal();

                if (user.getUseGoogle2Fa() && user.is2faRequired() && !request.getRequestURI().equals("/auth/verify2fa")) {
                    log.debug("2FA Required");

                    google2faFailureHandler.onAuthenticationFailure(request, response, null);
                    return;
                }

            }
        }

        filterChain.doFilter(request, response);
    }

}
