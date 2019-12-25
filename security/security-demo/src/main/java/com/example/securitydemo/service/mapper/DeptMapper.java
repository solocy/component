package com.example.securitydemo.service.mapper;

import com.example.securitydemo.common.BaseMapper;
import com.example.securitydemo.domain.Dept;
import com.example.securitydemo.service.dto.DeptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDTO, Dept> {
}
