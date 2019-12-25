package com.example.securitydemo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.securitydemo.common.execption.BadRequestException;
import com.example.securitydemo.common.util.QueryHelp;
import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.repository.MenuRepository;
import com.example.securitydemo.service.MenuService;
import com.example.securitydemo.service.dto.MenuDTO;
import com.example.securitydemo.service.dto.MenuQueryDTO;
import com.example.securitydemo.service.dto.RoleSmallDTO;
import com.example.securitydemo.service.dto.vo.MenuMetaVo;
import com.example.securitydemo.service.dto.vo.MenuVo;
import com.example.securitydemo.service.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
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
        return menuRepository.findById(id).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "未找到当前数据"));
    }

    @Override
    public List<MenuDTO> findQuery(MenuQueryDTO menu) {
        return menuMapper.toDto(menuRepository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, menu, criteriaBuilder)));
    }

    @Override
    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<MenuDTO> treeBuilder(List<MenuDTO> menus) {
        List<MenuDTO> menuDTOS = new ArrayList<>();
        menus.forEach(menu -> {
            if (Objects.isNull(menu.getPid()) || menu.getPid()==0) {
                menuDTOS.add(menu);
            }
            menus.forEach(menuDTO -> {
                if (menu.getId().equals(menuDTO.getPid())) {
                    if (Objects.isNull(menu.getChildren())) {
                        menu.setChildren(new ArrayList<>());
                    }
                    menu.getChildren().add(menuDTO);
                }
            });
        });
        return menuDTOS;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuDTO> menuDTOS) {
        List<MenuVo> list = new LinkedList<>();
        menuDTOS.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDTOList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(menuDTO.getName());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == null || menuDTO.getPid()==0 ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid() == null || menuDTO.getPid() == 0) {
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(), menuDTO.getIcon(), true));
                        if (menuDTOList != null && menuDTOList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == null || menuDTO.getPid() == 0) {
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    @Override
    public List<MenuDTO> findByRoles(List<RoleSmallDTO> roles) {
        Set<Menu> menus = new LinkedHashSet<>();
        for (RoleSmallDTO role : roles) {
            List<Menu> menus1 = new ArrayList<>(menuRepository.findByRoles_IdAndTypeNotInOrderBySortAsc(role.getId(),"permission"));
            menus.addAll(menus1);
        }
        return menus.stream().map(menuMapper::toDto).distinct().collect(Collectors.toList());
    }
}
