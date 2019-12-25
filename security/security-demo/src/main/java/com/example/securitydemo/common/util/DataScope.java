package com.example.securitydemo.common.util;

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


    public List<Long> getDeptAndChildrenId(List<Long> deptIds) {
        List<Long> longs = new ArrayList<>();
        deptIds.forEach(id -> {
                    List<Long> ids = deptService.getDeptChildren(id).stream().map(Dept::getId).collect(Collectors.toList());
                    longs.addAll(ids);
                    longs.addAll( getDeptAndChildrenId(ids));
                });
        longs.addAll(deptIds);
        return longs;
    }
}
