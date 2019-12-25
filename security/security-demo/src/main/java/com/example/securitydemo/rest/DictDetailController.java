package com.example.securitydemo.rest;

import com.example.securitydemo.domain.DictDetail;
import com.example.securitydemo.service.DictDetailService;
import com.example.securitydemo.service.dto.DictDetailQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@RestController
@Api(tags = "系统：字典详情管理")
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    private final DictDetailService dictDetailService;


    public DictDetailController(DictDetailService dictDetailService) {
        this.dictDetailService = dictDetailService;
    }

    @ApiOperation("查询字典详情")
    @PutMapping("/query")
    @PreAuthorize("hasAuthority('dict:query')")
    public ResponseEntity getDictDetails(@RequestBody DictDetailQueryDTO criteria){
        return new ResponseEntity<>(dictDetailService.queryAll(criteria), HttpStatus.OK);
    }

    @ApiOperation("新增字典详情")
    @PostMapping
    @PreAuthorize("hasAuthority('dict:add')")
    public ResponseEntity create(@Validated @RequestBody DictDetail resources){
        return new ResponseEntity<>(dictDetailService.create(resources), HttpStatus.CREATED);
    }

    @ApiOperation("修改字典详情")
    @PutMapping
    @PreAuthorize("hasAuthority('dict:edit')")
    public ResponseEntity update(@RequestBody DictDetail resources){
        dictDetailService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除字典详情")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('dict:del')")
    public ResponseEntity delete(@PathVariable Long id){
        dictDetailService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}