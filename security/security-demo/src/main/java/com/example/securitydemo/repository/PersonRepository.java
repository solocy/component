package com.example.securitydemo.repository;

import com.example.securitydemo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor {
}
