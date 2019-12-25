package com.example.securitydemo.service;

import com.example.securitydemo.domain.DictDetail;
import com.example.securitydemo.service.dto.DictDetailDTO;
import com.example.securitydemo.service.dto.DictDetailQueryDTO;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
public interface DictDetailService {

    DictDetailDTO findById(Long id);

    DictDetailDTO create(DictDetail resources);

    void update(DictDetail resources);

    void delete(Long id);

    List<DictDetail> queryAll(DictDetailQueryDTO criteria);
}