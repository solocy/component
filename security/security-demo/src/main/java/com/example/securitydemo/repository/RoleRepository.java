package com.example.securitydemo.repository;

import com.example.securitydemo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor {
}
