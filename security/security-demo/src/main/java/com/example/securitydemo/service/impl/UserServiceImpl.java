package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.BadRequestExecption;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.repository.UserRepository;
import com.example.securitydemo.service.UserService;
import com.example.securitydemo.service.dto.UserDTO;
import com.example.securitydemo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO create(User user) {
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(User user) {
        return userMapper.toDto(userRepository.save(user));
    }
    @Override
    public UserDTO get(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new BadRequestExecption(HttpStatus.BAD_REQUEST,"未找到当前数据")));
    }

    @Override
    public List<UserDTO> findQuery(User user) {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
