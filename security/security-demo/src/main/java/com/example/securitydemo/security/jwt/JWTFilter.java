package com.example.securitydemo.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
@Component
public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";


    public static final String AUTHORIZATION_TOKEN = "access_token";

    @Autowired
    private TokenProvider tokenProvider;

    private Map<String, String> token = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) this.tokenProvider.getAuthentication(jwt);
            String loginIp = httpServletRequest.getRemoteAddr();
            String userAgent = httpServletRequest.getHeader("User-Agent");
            authentication.setDetails(Arrays.asList(loginIp, userAgent));
            if ("system".equals(authentication.getName())) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // 使用token 登录时间判断登录先后顺序
                String oldToken = token.get(authentication.getName());
                if (Objects.isNull(oldToken)) {
                    token.put(authentication.getName(), jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    if (Objects.nonNull(tokenProvider.getCreateTime(jwt))) {
                        if (jwt.equals(oldToken) || Objects.isNull(tokenProvider.getCreateTime(oldToken))
                            || tokenProvider.getCreateTime(oldToken).before(tokenProvider.getCreateTime(jwt))) {
                            token.put(authentication.getName(), jwt);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        String jwt = request.getParameter(AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(jwt)) {
            return jwt;
        }
        return null;
    }
}
