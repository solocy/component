package com.example.securitydemo.domain;


import com.example.securitydemo.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "demo_user")
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long id;

    @Column(name = "demo_account")
    private String account;  // 账号

    @Column(name = "demo_password")
    private String password;  // 密码

    @Column(name = "demo_name")
    private String name;  //姓名

    @Column(name = "demo_phone")
    private String phone; // 电话

    @Column(name = "demo_email")
    private String email; // 邮箱

    @ManyToMany
    @JoinTable(name = "demo_user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "demo_id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "demo_id")})
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;
}
