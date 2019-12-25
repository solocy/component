package com.example.securitydemo.service.mapper;

import com.example.securitydemo.common.BaseMapper;
import com.example.securitydemo.domain.Person;
import com.example.securitydemo.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper extends BaseMapper<PersonDTO, Person> {
}
