package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.service.RegistrationService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
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
    private final RegistrationService registrationService;
    private final GoogleAuthenticator googleAuthenticator;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        registrationService.register(new User(username, password));
        return "redirect:/auth/login";
    }

    @GetMapping("/2fa")
    public String hello(Model model) {
        String url = GoogleAuthenticatorQRGenerator.getOtpAuthURL("HOMER", "John",
                googleAuthenticator.createCredentials("John"));

        model.addAttribute("googleurl", url);
        return "auth/2fa";
    }
}
