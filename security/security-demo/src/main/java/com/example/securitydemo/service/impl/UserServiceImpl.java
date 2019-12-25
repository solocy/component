package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.execption.BadRequestException;
import com.example.securitydemo.common.util.PageInfo;
import com.example.securitydemo.common.util.PageUtil;
import com.example.securitydemo.common.util.QueryHelp;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.repository.UserRepository;
import com.example.securitydemo.service.UserService;
import com.example.securitydemo.service.dto.UserDTO;
import com.example.securitydemo.service.dto.UserQueryDTO;
import com.example.securitydemo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(User user) {
        return userMapper.toDto(userRepository.save(user));
    }
    @Override
    public UserDTO get(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST,"未找到当前数据")));
    }

    @Override
    public List<UserDTO> findQuery(UserQueryDTO user) {
        return userMapper.toDto(userRepository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,user,criteriaBuilder)));
    }


    @Override
    public Map<String,Object> findQuery(UserQueryDTO user, PageInfo pageable) {
         Page<User> users = userRepository.findAll((Specification) (root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,user,criteriaBuilder),PageInfo.toPageable(pageable));
         return PageUtil.toPage(users.map(userMapper::toDto));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
