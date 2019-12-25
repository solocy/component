package com.example.securitydemo.service;

import com.example.securitydemo.domain.Role;

import java.util.List;

public interface RoleService {

    Role create(Role role);
    Role update(Role role);

    Role get(Long id);

    List<Role> findQuery(Role role);

    void delete(Long id);
}
