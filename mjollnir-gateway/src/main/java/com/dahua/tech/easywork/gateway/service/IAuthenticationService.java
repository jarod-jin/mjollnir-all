package com.dahua.tech.easywork.gateway.service;

import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @auther jarod.jin 2018/12/7
 */
public interface IAuthenticationService {
    ResultDTO queryUserInfoByUsername(UserDTO user);
}
