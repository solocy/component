package com.example.securitydemo.service.dto;

import com.example.securitydemo.common.annotation.QueryHelper;
import lombok.Data;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Data
public class DictDetailQueryDTO {

    @QueryHelper(type = QueryHelper.Type.INNER_LIKE)
    private String label;

    @QueryHelper(propName = "name",joinName = "dict")
    private String dictName;
}