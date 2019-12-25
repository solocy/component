package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.execption.BadRequestException;
import com.example.securitydemo.common.util.QueryHelp;
import com.example.securitydemo.domain.Role;
import com.example.securitydemo.repository.RoleRepository;
import com.example.securitydemo.service.RoleService;
import com.example.securitydemo.service.dto.RoleQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
        return roleRepository.findById(id).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST,"未找到当前数据"));
    }

    @Override
    public List<Role> findQuery(RoleQueryDTO role) {
        return roleRepository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,role,criteriaBuilder));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
