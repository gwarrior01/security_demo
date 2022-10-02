package com.example.demo.web;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/hello")
    public String hello(Principal principal) {
        System.out.println("User has name: " + principal.getName());
        return "hello";
    }
}
