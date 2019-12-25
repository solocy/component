package com.example.securitydemo.service;

import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.service.dto.DeptDTO;
import com.example.securitydemo.service.dto.DeptQueryDTO;

import java.util.List;

public interface DeptService {
    Dept create(Dept dept);

    Dept update(Dept dept);

    List<DeptDTO> findQuery(DeptQueryDTO dept);

    Dept get(Long id);

    void delete(Long id);

    List<Dept> getDeptChildren(Long id);

    List<DeptDTO> treeBuilder(List<DeptDTO> dtos);
}
