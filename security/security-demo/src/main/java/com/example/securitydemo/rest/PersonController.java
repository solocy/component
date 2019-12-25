package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Person;
import com.example.securitydemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {



    @Autowired
    private PersonService personService;


    @PostMapping("/person")
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/person")
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @GetMapping("/person/{id}")
    public Person get(@PathVariable Long id) {
        return personService.get(id);
    }

    @PutMapping("/person/query")
    public List<Person> get(@RequestBody Person person) {
        return personService.findQuery(person);
    }

    @DeleteMapping("/person")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
