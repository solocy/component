package com.example.securitydemo.security.rest;

import com.example.securitydemo.common.execption.BadRequestException;
import com.example.securitydemo.common.util.SecurityUtils;
import com.example.securitydemo.security.jwt.JWTFilter;
import com.example.securitydemo.security.jwt.TokenProvider;
import com.example.securitydemo.security.rest.vm.LoginVM;
import com.example.securitydemo.service.UserService;
import com.example.securitydemo.service.dto.UserDTO;
import com.example.securitydemo.service.dto.UserQueryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "系统：登录")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());


        Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"用户名或密码错误");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.getRememberMe() == null) ? false : loginVM.getRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setAccount(loginVM.getUsername());
        UserDTO user = userService.findQuery(queryDTO).get(0);
        Map<String,Object> result = new HashMap<>();
        result.put("token",new JWTToken(jwt));
        result.put("user",user);
        return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }


    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity getUserInfo(){
        UserQueryDTO queryDTO = new UserQueryDTO();
        queryDTO.setAccount(SecurityUtils.getUsername());
        UserDTO user = userService.findQuery(queryDTO).get(0);
        return ResponseEntity.ok(user);
    }
//    /**
//     *  直接访问接口，模拟登录，可获取token
//     *
//     * @param param
//     * @return
//     */
//    @GetMapping("/authenticate/{param}")
//    @ApiOperation("获取token接口")
//    public ResponseEntity<String> authorize(@PathVariable String param) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//            new UsernamePasswordAuthenticationToken("system", "system");
//
//        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = tokenProvider.createToken(authentication, true);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, jwt);
//        return new ResponseEntity<>(param, httpHeaders, HttpStatus.OK);
//    }
}
