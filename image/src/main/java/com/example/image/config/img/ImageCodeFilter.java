package com.example.image.config.img;

import com.example.image.config.exception.ValidateCodeException;
import org.springframework.beans.factory.InitializingBean;
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

public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {


    /**
     * 需要验证到url 地址
     *
     */
    private Set<String> url = new HashSet<>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        url.add("/api/test");
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
            } catch (ValidateCodeException e) {
                httpServletResponse.setStatus(400);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 对比验证码
     *
     * @param servletWebRequest
     */
    private void validateCode(HttpServletRequest servletWebRequest) {
        String ip = servletWebRequest.getRemoteAddr().trim();
        logger.info("ip is " + ip);
        ImageCode imageCode = CodeCookie.getCookie(ip);
        String codeInRequest = servletWebRequest.getHeader("imageCode");

        if (Objects.isNull(codeInRequest)) {
            logger.info("验证码不能为空");
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (Objects.isNull(imageCode)) {
            logger.info("未找到对应信息");
            throw new ValidateCodeException("未找到对应信息");
        }
        if (imageCode.isExpire()) {
            CodeCookie.removeCookie(ip);
            logger.info("验证码已过期，请重新发送！");
            throw new ValidateCodeException("验证码已过期，请重新发送！");
        }
        logger.info(codeInRequest);
        logger.info(imageCode);
        if (!Objects.equals(imageCode.getCode(), codeInRequest)) {
            logger.info("验证码不正确！");
            throw new ValidateCodeException("验证码不正确！");
        }
        CodeCookie.removeCookie(ip);
    }
}
