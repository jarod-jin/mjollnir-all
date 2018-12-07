package com.dahua.tech.easywork.gateway.controller.filter;

import com.alibaba.fastjson.JSON;
import com.dahua.tech.easywork.api.enums.AuthReturnCode;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.gateway.service.TokenAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @auther jarod.jin 2018/12/3
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = null;
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
        } catch (SignatureException e) {
            log.info("签名校验失败 ", e.getMessage());
            response.getWriter().print(JSON.toJSONString(new ResultDTO(AuthReturnCode.AU411.name(), AuthReturnCode.AU411.getMsg(), "")));
            response.getWriter().close();
        }catch (ExpiredJwtException e){
            log.info("Token已过期 ", e.getMessage());
            response.getWriter().print(JSON.toJSONString(new ResultDTO(AuthReturnCode.AU412.name(), AuthReturnCode.AU412.getMsg(), "")));
            response.getWriter().close();
        }catch (Exception e){
            log.info("鉴权失败 ", e.getMessage());
            response.getWriter().print(JSON.toJSONString(new ResultDTO(AuthReturnCode.AU413.name(), AuthReturnCode.AU413.getMsg(),"")));
            response.getWriter().close();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
