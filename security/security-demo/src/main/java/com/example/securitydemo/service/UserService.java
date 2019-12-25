package com.example.securitydemo.service;

import com.example.securitydemo.common.util.PageInfo;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.service.dto.UserDTO;
import com.example.securitydemo.service.dto.UserQueryDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDTO create(User user);
    UserDTO update(User user);

    UserDTO get(Long id);

    List<UserDTO> findQuery(UserQueryDTO user);

    Map<String,Object> findQuery(UserQueryDTO user, PageInfo pageable);

    void delete(Long id);
}
