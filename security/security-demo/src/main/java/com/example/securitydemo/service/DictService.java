package com.example.securitydemo.service;

import com.example.securitydemo.domain.Dict;
import com.example.securitydemo.service.dto.DictDTO;
import com.example.securitydemo.service.dto.DictQueryDTO;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictService {

    List<DictDTO> queryAll(DictQueryDTO dict);

    DictDTO findById(Long id);

    DictDTO create(Dict resources);

    void update(Dict resources);

    void delete(Long id);

}