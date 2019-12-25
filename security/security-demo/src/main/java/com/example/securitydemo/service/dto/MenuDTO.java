package com.example.securitydemo.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {

    private Long id;

    private String name;

    private Long sort;

    private String path;

    private String icon;

    private String component;

    private String permission;

    private Long pid;

    private List<MenuDTO> menuDTOS;
}
