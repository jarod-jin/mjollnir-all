package com.dahua.tech.easywork.gateway.service;

import com.alibaba.fastjson.JSON;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther jarod.jin 2018/12/3
 */
@Slf4j
@Component
public class TokenAuthenticationService {

    /**
     * 过期时间 2小时
     */
    static final long EXPIRATIONTIME = 1000 * 60 * 60 * 2;
    /**
     * JWT 密码
     */
    static final String SECRET = "dahua.isdp";
    /**
     * TOKEN前缀
     */
    static final String TOKEN_PREFIX = "Bearer";
    /**
     * 存放Token的Header Key
     */
    static final String HEADER_STRING = "token";

    static final String AUTHORITIES = "authorities";

    public static void addAuthentication(HttpServletResponse response,  Authentication auth) {
        StringBuilder builder = new StringBuilder();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            builder.append(authority.getAuthority()).append(",");
        }
        String role = builder.toString().substring(0, builder.length() - 1);
        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim(AUTHORITIES, role)
                // 用户名写入标题
                .setSubject(auth.getName())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        // 将 JWT 写入 body
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            log.info("授权返回 "+auth.getName()+""+auth.toString());
            response.getWriter().print(JSON.toJSONString(new ResultDTO("AU200", "令牌获得成功", JWT)));
            response.getWriter().close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES));

            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities)
                    : null;
        }
        return null;
    }

}
