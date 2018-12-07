package com.dahua.tech.easywork.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.api.dto.platform.UserRoleDTO;
import com.dahua.tech.easywork.api.enums.PlatformReturnCode;
import com.dahua.tech.easywork.api.service.PlatformService;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.gateway.exception.UsernameIsExitedException;
import com.dahua.tech.easywork.gateway.model.CustomGrantedAuthority;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @auther jarod.jin 2018/12/3
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PlatformService platformServerClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        ResultDTO resultDTO = platformServerClient.queryUser(userDTO);
        if (resultDTO.getResultCode().equals(PlatformReturnCode.PLU200.name())) {
            String jsonStr  = JSON.toJSONString(resultDTO.getData());
            UserDTO user = JSON.parseObject(jsonStr,UserDTO.class);
            List<CustomGrantedAuthority> grantedAuthorityList = Lists.newArrayList();
            for (UserRoleDTO urd :user. getRoleDTOList() ){
                grantedAuthorityList.add(new CustomGrantedAuthority(urd.getRoleName()));
            }
            return new User(userDTO.getUsername(), user.getPassword(), grantedAuthorityList);
        }
        throw new UsernameIsExitedException("该用户不存在");
    }
}
