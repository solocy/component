package com.example.securitydemo.service.dto;

import com.example.securitydemo.common.annotation.QueryHelper;
import lombok.Data;

@Data
public class DeptQueryDTO {


    @QueryHelper
    private Long id;

    @QueryHelper(type = QueryHelper.Type.INNER_LIKE)
    private String name;

    @QueryHelper
    private Long pid;

}
