package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/a")
    public String a() {
        return "a";
    }

    @GetMapping("/a/{value}")
    public String value(@PathVariable String value) {
        return value;
    }

    @GetMapping("/b")
    public String b() {
        return "b";
    }

    @GetMapping("/a/b")
    public String ab() {
        return "ab";
    }

    @GetMapping("/a/b/c")
    public String abc() {
        return "abc";
    }

    @GetMapping("/a/b/c/d")
    public String abcd() {
        return "abcd";
    }

    @GetMapping("/c/{country}")
    public String country(@PathVariable String country) {
        return country;
    }

    @GetMapping("/c/d/{country}")
    public String dCountry(@PathVariable String country) {
        return country;
    }


}
