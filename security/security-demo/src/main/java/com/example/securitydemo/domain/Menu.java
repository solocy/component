package com.example.securitydemo.domain;

import com.example.securitydemo.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Table(name = "demo_menu")
@Entity
public class Menu extends BaseEntity implements Serializable {

    @Id
    @Column(name = "demo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demo_name")
    private String name;   // 菜单名

    @Column(name = "demo_sort")
    private Long sort;     // 排序

    @Column(name = "demo_path")
    private String path;   // 地址

    @Column(name = "demo_icon")
    private String icon;  // 图片

    @Column(name = "demo_component")
    private String component;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean hidden;

    // 是否为外链 true/false
    @Column(name = "i_frame")
    private Boolean iFrame;

    @Column(name = "demo_type")
    private String type; // 类型  menu Permission  Directory

    @Column(name = "demo_permission")
    private String permission;  // 权限标示

    @Column(name = "demo_pid")
    private Long pid;  // 上级菜单


    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Menu menu = (Menu) o;

        return id != null ? id.equals(menu.id) : menu.id == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
