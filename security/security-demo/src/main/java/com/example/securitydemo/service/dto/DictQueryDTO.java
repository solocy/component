package com.example.securitydemo.service.dto;

import com.example.securitydemo.common.annotation.QueryHelper;
import lombok.Data;

/**
 * 公共查询类
 */
@Data
public class DictQueryDTO {

    // 多字段模糊
    @QueryHelper(blurry = "name,remark")
    private String blurry;
}
