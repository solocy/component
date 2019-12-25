package com.example.securitydemo.service.mapper;

import com.example.securitydemo.common.base.BaseMapper;
import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDTO, Menu> {
    @Override

//    @Mapping(target = "roles",ignore = true)
    MenuDTO toDto(Menu entity);
}
