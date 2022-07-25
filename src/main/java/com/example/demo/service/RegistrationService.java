package com.example.demo.service;

import com.example.demo.entity.Person;

import java.util.Optional;

public interface RegistrationService {

    Optional<Person> loadUserByUsername(String username);

    void register(Person person);
}
