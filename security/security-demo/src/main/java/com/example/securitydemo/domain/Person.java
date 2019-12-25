package com.example.securitydemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "demo_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long id;

    @Column(name = "demo_name")
    private String name;

    @Column(name = "demo_phone")
    private String phone;

    @Column(name = "demo_sex")
    private String sex;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;
}
