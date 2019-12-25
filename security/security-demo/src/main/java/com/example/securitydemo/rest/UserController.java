package com.example.securitydemo.rest;

import com.example.securitydemo.domain.User;
import com.example.securitydemo.service.UserService;
import com.example.securitydemo.service.dto.UserDTO;
import com.example.securitydemo.service.dto.UserQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "系统：用户管理")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("创建用户")
    @PostMapping
    @PreAuthorize("hasAuthority('user:create')")
    public UserDTO create(@RequestBody User user) {
        return userService.create(user);
    }

    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("hasAuthority('user:update')")
    public UserDTO update(@RequestBody User user) {
        return userService.update(user);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:get')")
    public UserDTO get(@PathVariable Long id) {
        return userService.get(id);
    }

    @ApiOperation("查询所有满足条件的用户信息")
    @PutMapping("/query")
    @PreAuthorize("hasAuthority('user:query')")
    public List<UserDTO> get(@RequestBody UserQueryDTO user) {
        return userService.findQuery(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
