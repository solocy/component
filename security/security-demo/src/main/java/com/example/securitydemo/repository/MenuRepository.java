package com.example.securitydemo.repository;

import com.example.securitydemo.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuRepository extends JpaRepository<Menu,Long>, JpaSpecificationExecutor {
}
