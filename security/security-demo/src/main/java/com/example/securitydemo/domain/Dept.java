package com.example.securitydemo.domain;

import com.example.securitydemo.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "demo_dept")
public class Dept extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_id")
    private Long id;

    @Column(name = "demo_name")
    private String name;   // 部门名

    @Column(name = "demo_pid")
    private Long pid;     // 上级部门id

    @Column(name = "demo_sort")
    private Long sort;    // 排序
}