package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Optional;

public interface RegistrationService {

    Optional<User> loadUserByUsername(String username);

    void register(User person);
}
