package com.example.securitydemo.domain;

import com.example.securitydemo.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "demo_menu")
@Entity
public class Menu extends BaseEntity {

    @Id
    @Column(name = "demo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demo_name")
    private String name;

    @Column(name = "demo_sort")
    private Long sort;

    @Column(name = "demo_path")
    private String path;

    @Column(name = "demo_icon")
    private String icon;

    @Column(name = "demo_component")
    private String component;

    @Column(name = "demo_type")
    private String type;

    @Column(name = "demo_permission")
    private String permission;

    @Column(name = "demo_pid")
    private Long pid;
}
