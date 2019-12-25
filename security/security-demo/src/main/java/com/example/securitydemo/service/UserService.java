package com.example.securitydemo.service;

import com.example.securitydemo.domain.User;
import com.example.securitydemo.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(User user);
    UserDTO update(User user);

    UserDTO get(Long id);

    List<UserDTO> findQuery(User user);

    void delete(Long id);
}
