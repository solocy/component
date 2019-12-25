package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.BadRequestExecption;
import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.repository.MenuRepository;
import com.example.securitydemo.service.MenuService;
import com.example.securitydemo.service.dto.MenuDTO;
import com.example.securitydemo.service.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        return menuRepository.save(menu);
    }
    @Override
    public Menu get(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new BadRequestExecption(HttpStatus.BAD_REQUEST,"未找到当前数据"));
    }

    @Override
    public List<MenuDTO> findQuery(Menu menu) {
        return menuMapper.toDto(menuRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<MenuDTO> treeBuilder(List<MenuDTO> menus) {
        List<MenuDTO> menuDTOS = new ArrayList<>();
        menus.forEach(menu -> {
            if (Objects.isNull(menu.getPid())) {
                menuDTOS.add(menu);
            }
            menus.forEach(menuDTO -> {
                if (menu.getId().equals(menuDTO.getPid())) {
                    if (Objects.isNull(menu.getMenuDTOS())) {
                        menu.setMenuDTOS(new ArrayList<>());
                    }
                    menu.getMenuDTOS().add(menuDTO);
                }
            });
        });
        return menuDTOS;
    }
}
