package com.example.demo.util;

import com.example.demo.entity.Person;
import com.example.demo.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {

    private final RegistrationService personService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        personService.loadUserByUsername(person.getUsername())
                .ifPresent(p -> errors.rejectValue("username", "",
                        "Человек с таким именем пользователя существует")
                );
    }
}
