package com.example.securitydemo.service;

import com.example.securitydemo.domain.Person;

import java.util.List;

public interface PersonService {

    Person create(Person person);
    Person update(Person person);

    Person get(Long id);

    List<Person> findQuery(Person person);

    void delete(Long id);
}
