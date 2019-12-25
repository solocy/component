package com.example.securitydemo.rest;

import com.example.securitydemo.domain.User;
import com.example.securitydemo.service.UserService;
import com.example.securitydemo.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public UserDTO create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/user")
    public UserDTO update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/user/{id}")
    public UserDTO get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping("/user/query")
    public List<UserDTO> get(@RequestBody User user) {
        return userService.findQuery(user);
    }

    @DeleteMapping("/user")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
