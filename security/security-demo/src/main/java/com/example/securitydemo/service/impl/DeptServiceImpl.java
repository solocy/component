package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.execption.BadRequestException;
import com.example.securitydemo.common.util.QueryHelp;
import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.repository.DeptRepository;
import com.example.securitydemo.service.DeptService;
import com.example.securitydemo.service.dto.DeptDTO;
import com.example.securitydemo.service.dto.DeptQueryDTO;
import com.example.securitydemo.service.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
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
    public List<DeptDTO> findQuery(DeptQueryDTO dept) {
        return deptMapper.toDto(deptRepository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,dept,criteriaBuilder)));
    }

    @Override
    public Dept get(Long id) {
        return deptRepository.findById(id).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST,"未找到当前数据"));
    }

    @Override
    public void delete(Long id) {
        deptRepository.deleteById(id);
    }

    @Override
    public List<Dept> getDeptChildren(Long id) {
        return deptRepository.findByPid(id);
    }

    @Override
    public List<DeptDTO> treeBuilder(List<DeptDTO> dtos) {
        List<DeptDTO> deptDTOS = new ArrayList<>();
        dtos.forEach(deptDTO -> {
            if (Objects.isNull(deptDTO.getPid())) {
                deptDTOS.add(deptDTO);
            }
            dtos.forEach(menuDTO -> {
                if (deptDTO.getId().equals(menuDTO.getPid())) {
                    if (Objects.isNull(deptDTO.getChildren())) {
                        deptDTO.setChildren(new ArrayList<>());
                    }
                    deptDTO.getChildren().add(menuDTO);
                }
            });
        });
        return deptDTOS;
    }
}
