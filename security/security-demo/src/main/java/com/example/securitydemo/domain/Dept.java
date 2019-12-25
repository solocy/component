package com.example.securitydemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "demo_dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long id;

    @Column(name = "demo_name")
    private String name;

    @Column(name = "demo_pid")
    private Long pid;

    @Column(name = "demo_sort")
    private Long sort;
}
