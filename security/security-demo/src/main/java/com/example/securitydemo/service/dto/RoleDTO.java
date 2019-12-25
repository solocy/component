package com.example.securitydemo.service.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDTO {

    private Long id;

    private String name;

    private String remark;

    private Set<MenuDTO> menus = new HashSet<>();


    private Set<DeptDTO> depts = new HashSet<>();
}
