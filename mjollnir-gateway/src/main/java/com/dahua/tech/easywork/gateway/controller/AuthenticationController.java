package com.dahua.tech.easywork.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.api.service.PlatformService;
import com.dahua.tech.easywork.gateway.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * @auther jarod.jin 2018/12/3
 */
@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private IAuthenticationService AuthenticationService;

    // 路由映射到/users 并且限制请求类型仅仅为json
    @RequestMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO userLogin(@RequestBody UserDTO userDTO) {
        return AuthenticationService.queryUserInfoByUsername(userDTO);
    }
}
