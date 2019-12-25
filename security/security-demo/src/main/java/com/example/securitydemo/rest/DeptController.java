package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @PostMapping("/dept")
    public Dept create(@RequestBody Dept dept) {
        return deptService.create(dept);
    }

    @PutMapping("/dept")
    public Dept update(@RequestBody Dept dept) {
        return deptService.update(dept);
    }

    @GetMapping("/dept/{id}")
    public Dept get(@PathVariable Long id) {
        return deptService.get(id);
    }

    @PutMapping("/dept/query")
    public List<Dept> get(@RequestBody Dept dept) {
        return deptService.findQuery(dept);
    }

    @DeleteMapping("/dept")
    public void delete(@PathVariable Long id) {
        deptService.delete(id);
    }

}
