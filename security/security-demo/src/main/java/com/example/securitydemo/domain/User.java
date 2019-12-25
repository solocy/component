package com.example.securitydemo.domain;


import com.example.securitydemo.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "demo_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long id;

    @Column(name = "demo_account")
    private String account;

    @Column(name = "demo_password")
    private String password;

    @Column(name = "demo_name")
    private String name;

    @Column(name = "demo_phone")
    private String phone;

    @Column(name = "demo_email")
    private String email;

    @ManyToMany
    @JoinTable(name = "demo_user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "demo_id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "demo_id")})
    private Set<Role> roles = new HashSet<>();
}
