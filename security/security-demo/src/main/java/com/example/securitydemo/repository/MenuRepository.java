package com.example.securitydemo.repository;

import com.example.securitydemo.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long>, JpaSpecificationExecutor {

    List<Menu> findByRoles_IdAndTypeNotInOrderBySortAsc(Long id, String type);
}
