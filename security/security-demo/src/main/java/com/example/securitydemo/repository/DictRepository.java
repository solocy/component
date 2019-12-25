package com.example.securitydemo.repository;

import com.example.securitydemo.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DictRepository extends JpaRepository<Dict,Long>, JpaSpecificationExecutor {
}
