package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @PostMapping("/transfer")
    public String transfer(@RequestParam BigDecimal amount, Principal principal) {
        System.out.println("User " + principal.getName() + " transfer his money " + amount);
        return "success";
    }

    @GetMapping("/transfer")
    public String hiddenTransfer(@RequestParam BigDecimal amount, Principal principal) {
        System.out.println("User " + principal.getName() + " transfer his money " + amount);
        return "success";
    }
}
