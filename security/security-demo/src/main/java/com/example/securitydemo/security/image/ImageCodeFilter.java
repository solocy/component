package com.example.securitydemo.security.image;

import com.example.securitydemo.common.execption.BadRequestException;
import com.example.securitydemo.security.image.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class ImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    private CodeService codeService;

    private Set<String> url = new HashSet<>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        url.add("/api/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        boolean match = false;
        for (String u : url) {
            if (pathMatcher.match(u, httpServletRequest.getRequestURI())) {
                match = true;
            }
        }
        if (match) {
            try {
                validateCode(httpServletRequest);
            } catch (AuthenticationException e) {
                httpServletResponse.setStatus(400);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(HttpServletRequest servletWebRequest) {
        String codeInRequest = servletWebRequest.getHeader("imageCode");
        String uuidInRequest = servletWebRequest.getHeader("imageUUID");
        if (Objects.isNull(uuidInRequest) || Objects.isNull(codeInRequest)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"验证码不能为空！");
        }
        String code = codeService.getCode(uuidInRequest);

        if (Objects.isNull(code)) {
            logger.info("验证码已过期，请重新发送！");
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"验证码已过期，请重新发送！");
        }
        if (!Objects.equals(code, codeInRequest)) {
            logger.info("验证码不正确！");
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"验证码不正确！");
        }
    }
}
