package com.example.securitydemo.service.dto;

import com.example.securitydemo.common.annotation.QueryHelper;
import lombok.Data;

@Data
public class UserQueryDTO {

    @QueryHelper
    private Long id;

    @QueryHelper
    private String account;

    @QueryHelper
    private String name;

    @QueryHelper
    private String phone;

    @QueryHelper
    private String email;

    @QueryHelper(propName = "id",joinName = "dept")
    private Long deptId;

}
