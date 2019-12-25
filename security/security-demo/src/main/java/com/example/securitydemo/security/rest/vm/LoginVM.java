package com.example.securitydemo.security.rest.vm;

import lombok.Data;

@Data
public class LoginVM {

    private String username;

    private String password;

    private Boolean rememberMe;
}
