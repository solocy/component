package com.example.securitydemo.service.dto;

import com.example.securitydemo.common.annotation.QueryHelper;
import lombok.Data;

@Data
public class MenuQueryDTO {

    @QueryHelper
    private Long id;

    @QueryHelper(type = QueryHelper.Type.INNER_LIKE)
    private String name;

    @QueryHelper
    private String path;

    private String component;

    @QueryHelper
    private String type;

    @QueryHelper
    private String permission;

    @QueryHelper
    private Long pid;
}
