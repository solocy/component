package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.BadRequestExecption;
import com.example.securitydemo.domain.Role;
import com.example.securitydemo.repository.RoleRepository;
import com.example.securitydemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public Role get(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new BadRequestExecption(HttpStatus.BAD_REQUEST,"未找到当前数据"));
    }

    @Override
    public List<Role> findQuery(Role role) {
        return roleRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
