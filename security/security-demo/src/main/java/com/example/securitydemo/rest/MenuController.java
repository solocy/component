package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.service.MenuService;
import com.example.securitydemo.service.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;


    @PostMapping("/menu")
    public Menu create(@RequestBody Menu menu) {
        return menuService.create(menu);
    }

    @PutMapping("/menu")
    public Menu update(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    @GetMapping("/menu/{id}")
    public Menu get(@PathVariable Long id) {
        return menuService.get(id);
    }

    @PutMapping("/menu/query")
    public List<MenuDTO> get(@RequestBody Menu menu) {
        List<MenuDTO> menuDTOS = menuService.findQuery(menu);
        return menuService.treeBuilder(menuDTOS);
    }

    @DeleteMapping("/menu")
    public void delete(@PathVariable Long id) {
        menuService.delete(id);
    }
}
