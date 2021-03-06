package com.example.demo.web;

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
