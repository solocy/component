package com.example.securitydemo.domain;


import com.example.securitydemo.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "demo_role")
@Entity
public class Role extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long id;

    @Column(name = "demo_name")
    private String name; //角色名

    @Column(name = "demo_remark")
    private String remark;  // 备注

    @ManyToMany
    @JoinTable(name = "demo_role_menu",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "demo_id")},inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "demo_id")})
    private Set<Menu> menus = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "demo_role_dept",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "demo_id")},inverseJoinColumns = {@JoinColumn(name = "dept_id",referencedColumnName = "demo_id")})
    private Set<Dept> depts = new HashSet<>();
}
