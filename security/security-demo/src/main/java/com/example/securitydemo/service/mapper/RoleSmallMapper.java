package com.example.securitydemo.service.mapper;

import com.example.securitydemo.common.BaseMapper;
import com.example.securitydemo.domain.Role;
import com.example.securitydemo.service.dto.RoleSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDTO, Role> {
}
