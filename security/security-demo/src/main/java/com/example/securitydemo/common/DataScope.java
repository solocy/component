package com.example.securitydemo.common;

import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataScope {

    @Autowired
    private DeptService deptService;


    public List<Long> getDeptAndChildrenId(List<Dept> depts) {
        List<Long> longs = new ArrayList<>();
        depts.forEach(dept -> {
                    List<Long> ids = deptService.getDeptChildren(dept.getId()).stream().map(Dept::getId).collect(Collectors.toList());
                    ids.add(dept.getId());
                    longs.addAll(ids);
                });
        return longs;
    }
}
