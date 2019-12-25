package com.example.securitydemo.service;

import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.service.dto.MenuDTO;
import com.example.securitydemo.service.dto.MenuQueryDTO;
import com.example.securitydemo.service.dto.RoleSmallDTO;
import com.example.securitydemo.service.dto.vo.MenuVo;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu);
    Menu update(Menu menu);

    Menu get(Long id);

    List findQuery(MenuQueryDTO menu);

    void delete(Long id);

    List<MenuDTO> treeBuilder(List<MenuDTO> menus);

    List<MenuDTO> findByRoles(List<RoleSmallDTO> roles);

    List<MenuVo> buildMenus(List<MenuDTO> menuDTOS);
}
