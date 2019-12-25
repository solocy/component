package com.example.securitydemo.domain;

import com.example.securitydemo.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Table(name = "demo_dict")
@Entity
public class Dict extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "demo_name")
    private String name;    // 字典名

    @Column(name = "demo_remark")
    private String remark;  // 备注

    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DictDetail> dictDetails;
}
