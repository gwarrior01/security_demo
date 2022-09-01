package com.example.demo.web;

import com.example.demo.web.dto.HelloRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String start() {
        System.out.println("Start was invoke");
        return "start";
    }

    @PostMapping("/hello")
//    @CrossOrigin(origins = "https://www.youtube.com", allowedHeaders = "Content-Type", methods = RequestMethod.POST)
    public String hello(@RequestBody HelloRequest request) {
        System.out.println("Hello was invoke");
        return "Hello, " + request.getName();
    }

    @PostMapping(value = "/hello", consumes = "text/plain")
    public String helloTextPlain(String message) {
        System.out.println("HelloTextPlain was invoke");
        return "Hello, " + message;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.OPTIONS)
    public ResponseEntity options(String message) {
        System.out.println("!!!!!!!!!!");
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Methods", "POST")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("Access-Control-Allow-Origin", "https://www.youtube.com/")
                .build();
    }


}
