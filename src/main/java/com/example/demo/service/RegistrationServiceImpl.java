package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final PersonRepository personRepository;

    @Override
    public Optional<Person> loadUserByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void register(Person person) {
        personRepository.save(person);
    }
}
