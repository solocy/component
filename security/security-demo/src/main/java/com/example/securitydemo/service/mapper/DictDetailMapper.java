package com.example.securitydemo.service.mapper;

import com.example.securitydemo.common.base.BaseMapper;
import com.example.securitydemo.domain.DictDetail;
import com.example.securitydemo.service.dto.DictDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDTO, DictDetail> {

}