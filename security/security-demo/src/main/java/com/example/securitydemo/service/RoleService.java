package com.example.securitydemo.service;

import com.example.securitydemo.domain.Role;
import com.example.securitydemo.service.dto.RoleQueryDTO;

import java.util.List;

public interface RoleService {

    Role create(Role role);
    Role update(Role role);

    Role get(Long id);

    List<Role> findQuery(RoleQueryDTO role);

    void delete(Long id);
}
