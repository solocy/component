package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.service.DeptService;
import com.example.securitydemo.service.dto.DeptDTO;
import com.example.securitydemo.service.dto.DeptQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "系统：部门管理")
@RequestMapping("/api/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("创建部门")
    @PostMapping
    @PreAuthorize("hasAuthority('dept:create')")
    public Dept create(@RequestBody Dept dept) {
        return deptService.create(dept);
    }

    @ApiOperation("修改部门")
    @PutMapping
    @PreAuthorize("hasAuthority('dept:update')")
    public Dept update(@RequestBody Dept dept) {
        return deptService.update(dept);
    }

    @ApiOperation("获取部门信息")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('dept:get')")
    public Dept get(@PathVariable Long id) {
        return deptService.get(id);
    }

    @ApiOperation("查询所有满足条件的部门")
    @PutMapping("/query")
    @PreAuthorize("hasAuthority('dept:query')")
    public List<DeptDTO> get(@RequestBody DeptQueryDTO dept) {
        return deptService.treeBuilder(deptService.findQuery(dept));
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('dept:delete')")
    public void delete(@PathVariable Long id) {
        deptService.delete(id);
    }

}
