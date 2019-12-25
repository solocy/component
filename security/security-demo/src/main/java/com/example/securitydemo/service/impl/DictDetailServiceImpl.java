package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.util.QueryHelp;
import com.example.securitydemo.domain.DictDetail;
import com.example.securitydemo.repository.DictDetailRepository;
import com.example.securitydemo.service.DictDetailService;
import com.example.securitydemo.service.dto.DictDetailDTO;
import com.example.securitydemo.service.dto.DictDetailQueryDTO;
import com.example.securitydemo.service.mapper.DictDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-04-10
 */
@Service
@Transactional
public class DictDetailServiceImpl implements DictDetailService {

    private final DictDetailRepository dictDetailRepository;

    private final DictDetailMapper dictDetailMapper;

    public DictDetailServiceImpl(DictDetailRepository dictDetailRepository, DictDetailMapper dictDetailMapper) {
        this.dictDetailRepository = dictDetailRepository;
        this.dictDetailMapper = dictDetailMapper;
    }

    @Override
    public List<DictDetail> queryAll(DictDetailQueryDTO criteria) {
        return dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
    }

    @Override
    public DictDetailDTO findById(Long id) {
        DictDetail dictDetail = dictDetailRepository.findById(id).orElseGet(DictDetail::new);
        return dictDetailMapper.toDto(dictDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DictDetailDTO create(DictDetail resources) {
        return dictDetailMapper.toDto(dictDetailRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DictDetail resources) {
        DictDetail dictDetail = dictDetailRepository.findById(resources.getId()).orElseGet(DictDetail::new);
        resources.setId(dictDetail.getId());
        dictDetailRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dictDetailRepository.deleteById(id);
    }
}