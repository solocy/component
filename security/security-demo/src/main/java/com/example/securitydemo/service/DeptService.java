package com.example.securitydemo.service;

import com.example.securitydemo.domain.Dept;

import java.util.List;

public interface DeptService {
    Dept create(Dept dept);

    Dept update(Dept dept);

    List<Dept> findQuery(Dept dept);

    Dept get(Long id);

    void delete(Long id);

    List<Dept> getDeptChildren(Long id);
}
