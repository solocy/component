package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Role;
import com.example.securitydemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @PutMapping("/role")
    public Role update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @GetMapping("/role/{id}")
    public Role get(@PathVariable Long id) {
        return roleService.get(id);
    }

    @PutMapping("/role/query")
    public List<Role> get(@RequestBody Role role) {
        return roleService.findQuery(role);
    }

    @DeleteMapping("/role")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
