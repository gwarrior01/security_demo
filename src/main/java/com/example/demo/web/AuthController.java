package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleAuthenticator googleAuthenticator;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register2fa")
    public String hello(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String url = GoogleAuthenticatorQRGenerator.getOtpAuthURL("HOMER-SEC-DEMO", username,
                googleAuthenticator.createCredentials(username));

        model.addAttribute("googleurl", url);
        return "auth/register2fa";
    }

    @PostMapping("/register2fa")
    public String confirm2Fa(@RequestParam Integer verifyCode) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User savedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(":("));

        if (googleAuthenticator.authorizeUser(username, verifyCode)) {
//            savedUser.setUseGoogle2Fa(true);
//            userRepository.save(savedUser);

            return "/index";
        } else {
            // bad code
            return "auth/register2fa";
        }
    }
}
