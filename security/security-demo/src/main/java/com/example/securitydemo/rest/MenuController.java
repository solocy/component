package com.example.securitydemo.rest;

import com.example.securitydemo.common.util.SecurityUtils;
import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.service.MenuService;
import com.example.securitydemo.service.RoleService;
import com.example.securitydemo.service.UserService;
import com.example.securitydemo.service.dto.MenuDTO;
import com.example.securitydemo.service.dto.MenuQueryDTO;
import com.example.securitydemo.service.dto.UserDTO;
import com.example.securitydemo.service.dto.UserQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "系统：菜单管理")
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("创建菜单权限")
    @PostMapping
    @PreAuthorize("hasAuthority('menu:create')")
    public Menu create(@RequestBody Menu menu) {
        return menuService.create(menu);
    }

    @ApiOperation("修改菜单权限")
    @PutMapping
    @PreAuthorize("hasAuthority('menu:update')")
    public Menu update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    @ApiOperation("获取菜单权限")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('menu:get')")
    public Menu get(@PathVariable Long id) {
        return menuService.get(id);
    }

    @ApiOperation("查询所有满足条件的菜单权限")
    @PutMapping("/query")
    @PreAuthorize("hasAuthority('menu:query')")
    public List<MenuDTO> get(@RequestBody MenuQueryDTO menu) {
        List<MenuDTO> menuDTOS = menuService.findQuery(menu);
        return menuService.treeBuilder(menuDTOS);
    }

    @ApiOperation("删除菜单权限")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('menu:delete')")
    public void delete(@PathVariable Long id) {
        menuService.delete(id);
    }

    @ApiOperation("构建前端路由所需要的菜单")
    @GetMapping(value = "/build")
    public ResponseEntity buildMenus() {
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setAccount(SecurityUtils.getUsername());
        UserDTO user = userService.findQuery(queryDTO).get(0);
        List<MenuDTO> menuDTOList = menuService.findByRoles(new ArrayList<>(user.getRoles()));
        List<MenuDTO> menuDTOTree = menuService.treeBuilder(menuDTOList);
        System.out.println(menuDTOTree);
        return new ResponseEntity(menuService.buildMenus(menuDTOTree), HttpStatus.OK);
    }
}
