package com.example.securitydemo.rest;

import com.example.securitydemo.domain.Dict;
import com.example.securitydemo.service.DictService;
import com.example.securitydemo.service.dto.DictQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "系统：字典管理")
@RestController
@RequestMapping("/api/dict")
public class DictController {

    private final DictService dictService;


    public DictController(DictService dictService) {
        this.dictService = dictService;
    }


    @ApiOperation("查询字典")
    @PutMapping("/query")
    @PreAuthorize("hasAuthority('dict:query')")
    public ResponseEntity getDicts(@RequestBody DictQueryDTO resources) {
        return new ResponseEntity<>(dictService.queryAll(resources), HttpStatus.OK);
    }

    @ApiOperation("新增字典")
    @PostMapping
    @PreAuthorize("hasAuthority('dict:add')")
    public ResponseEntity create(@Validated @RequestBody Dict resources){
        return new ResponseEntity<>(dictService.create(resources), HttpStatus.CREATED);
    }

    @ApiOperation("修改字典")
    @PutMapping
    @PreAuthorize("hasAuthority('dict:edit')")
    public ResponseEntity update(@RequestBody Dict resources){
        dictService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除字典")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('dict:del')")
    public ResponseEntity delete(@PathVariable Long id){
        dictService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}