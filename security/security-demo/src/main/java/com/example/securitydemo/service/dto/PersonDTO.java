package com.example.securitydemo.service.dto;

import lombok.Data;

@Data
public class PersonDTO {

    private Long id;

    private String name;

    private String phone;

    private String sex;

    private DeptDTO dept;
}
