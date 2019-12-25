package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.BadRequestExecption;
import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.repository.DeptRepository;
import com.example.securitydemo.service.DeptService;
import com.example.securitydemo.service.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Dept create(Dept dept) {
        return deptRepository.save(dept);
    }


    @Override
    public Dept update(Dept dept) {
        return deptRepository.save(dept);
    }

    @Override
    public List<Dept> findQuery(Dept dept) {
        return deptRepository.findAll();
    }

    @Override
    public Dept get(Long id) {
        return deptRepository.findById(id).orElseThrow(() -> new BadRequestExecption(HttpStatus.BAD_REQUEST,"未找到当前数据"));
    }

    @Override
    public void delete(Long id) {
        deptRepository.deleteById(id);
    }

    @Override
    public List<Dept> getDeptChildren(Long id) {
        return deptRepository.findByPid(id);
    }
}
