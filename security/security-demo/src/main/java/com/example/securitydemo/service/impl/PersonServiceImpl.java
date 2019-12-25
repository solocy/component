package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.BadRequestExecption;
import com.example.securitydemo.domain.Person;
import com.example.securitydemo.repository.PersonRepository;
import com.example.securitydemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }
    @Override
    public Person get(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new BadRequestExecption(HttpStatus.BAD_REQUEST,"未找到当前数据"));}

    @Override
    public List<Person> findQuery(Person person) {
        return personRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
