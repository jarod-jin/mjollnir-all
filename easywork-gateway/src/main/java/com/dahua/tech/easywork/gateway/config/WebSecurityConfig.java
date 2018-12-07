package com.dahua.tech.easywork.gateway.config;


import com.dahua.tech.easywork.gateway.controller.filter.JwtAuthenticationFilter;
import com.dahua.tech.easywork.gateway.controller.filter.JwtLoginFilter;
import com.dahua.tech.easywork.gateway.service.impl.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        // 使用自定义身份验证组件
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            // 对请求进行认证
            .authorizeRequests()
            // 所有OPTIONS请求全部放行
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 所有 / 的所有请求 都放行
            .antMatchers("/").permitAll()
            // 所有 /login 的POST请求 都放行
            .antMatchers(HttpMethod.POST, "/easywork/api/auth/login").permitAll()
            .antMatchers(HttpMethod.POST, "/userslogin").permitAll()
                // 权限检查
            .antMatchers("/hello").hasAuthority("AUTH_WRITE")
            // 角色检查
            .antMatchers("/world").hasRole("ADMIN")
            // 对Rest请求需要身份认证, 放行OPTIONS
            .antMatchers(HttpMethod.POST).authenticated()
            .antMatchers(HttpMethod.PUT).authenticated()
            .antMatchers(HttpMethod.DELETE).authenticated()
            .antMatchers(HttpMethod.GET).authenticated()
            .and()
            // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
            .addFilterBefore(new JwtLoginFilter("/easywork/api/auth/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            // 添加一个过滤器验证其他请求的Token是否合法
            .addFilterBefore(new JwtAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);
    }
}
