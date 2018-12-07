package com.dahua.tech.easywork.gateway.controller.filter;

import com.alibaba.fastjson.JSON;
import com.dahua.tech.easywork.api.dto.auth.AuthCredentials;
import com.dahua.tech.easywork.api.enums.AuthReturnCode;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.gateway.service.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther jarod.jin 2018/12/3
 */
@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JwtLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url, RequestMethod.POST.toString()));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        // JSON反序列化成 User
        AuthCredentials creds = JSON.parseObject(req.getInputStream(), AuthCredentials.class);
        // 返回一个验证令牌
        return getAuthenticationManager().
                authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),creds.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth) {
        TokenAuthenticationService.addAuthentication(res, auth);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(JSON.toJSONString(new ResultDTO(AuthReturnCode.AU413.name(), AuthReturnCode.AU413.getMsg(), "")));
        response.getWriter().close();
    }
}
