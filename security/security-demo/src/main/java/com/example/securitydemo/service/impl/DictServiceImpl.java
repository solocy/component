package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.util.QueryHelp;
import com.example.securitydemo.domain.Dict;
import com.example.securitydemo.repository.DictRepository;
import com.example.securitydemo.service.DictService;
import com.example.securitydemo.service.dto.DictDTO;
import com.example.securitydemo.service.dto.DictQueryDTO;
import com.example.securitydemo.service.mapper.DictMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Service
@Transactional
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;

    private final DictMapper dictMapper;

    public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper) {
        this.dictRepository = dictRepository;
        this.dictMapper = dictMapper;
    }



    @Override
    public List<DictDTO> queryAll(DictQueryDTO dict) {
        List<Dict> list = dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, dict, cb));
        return dictMapper.toDto(list);
    }

    @Override
    public DictDTO findById(Long id) {
        Dict dict = dictRepository.findById(id).orElseGet(Dict::new);
        return dictMapper.toDto(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDTO create(Dict resources) {
        return dictMapper.toDto(dictRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dict resources) {
        Dict dict = dictRepository.findById(resources.getId()).orElseGet(Dict::new);
        resources.setId(dict.getId());
        dictRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dictRepository.deleteById(id);
    }

}