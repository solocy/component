package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Role;
import com.example.securitydemo.service.RoleService;
import com.example.securitydemo.service.dto.RoleQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "系统：角色管理")
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("创建角色")
    @PostMapping
    @PreAuthorize("hasAuthority('role:create')")
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("hasAuthority('role:update')")
    public Role update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @ApiOperation("获取角色信息")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('role:get')")
    public Role get(@PathVariable Long id) {
        return roleService.get(id);
    }

    @ApiOperation("查询所有满足条件的角色")
    @PutMapping("/query")
    @PreAuthorize("hasAuthority('role:query')")
    public List<Role> get(@RequestBody RoleQueryDTO role) {
        return roleService.findQuery(role);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('role:delete')")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
