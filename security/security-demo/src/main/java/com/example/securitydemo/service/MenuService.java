package com.example.securitydemo.service;

import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.service.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    Menu create(Menu menu);
    Menu update(Menu menu);

    Menu get(Long id);

    List<MenuDTO> findQuery(Menu menu);

    void delete(Long id);

    List<MenuDTO> treeBuilder(List<MenuDTO> menus);
}
