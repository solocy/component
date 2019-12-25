package com.example.securitydemo.service.dto;

import com.example.securitydemo.common.annotation.QueryHelper;
import lombok.Data;

@Data
public class RoleQueryDTO {

    @QueryHelper
    private Long id;

    @QueryHelper(type = QueryHelper.Type.INNER_LIKE)
    private String name;

}
