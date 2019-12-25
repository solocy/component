package com.example.securitydemo.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeptDTO implements Serializable {

    private Long id;

    private String name;

    private Long pid;

    private List<DeptDTO> children;

    private Long sort;
}
