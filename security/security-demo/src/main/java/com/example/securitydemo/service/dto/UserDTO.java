package com.example.securitydemo.service.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    private String account;

    private String password;

    private String name;

    private String phone;

    private String email;

    private Set<RoleSmallDTO> roles = new HashSet<>();

    private DeptDTO dept;
}
