package com.dahua.tech.easywork.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.dahua.tech.easywork.api.dto.auth.AuthReturnDTO;
import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.api.enums.AuthReturnCode;
import com.dahua.tech.easywork.api.enums.PlatformReturnCode;
import com.dahua.tech.easywork.api.service.PlatformService;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.gateway.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther jarod.jin 2018/12/7
 */
@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private PlatformService platformServerClient;

    @Override
    public ResultDTO queryUserInfoByUsername(UserDTO userDTO){
        ResultDTO resultDTO = platformServerClient.queryUser(userDTO);
        log.info(resultDTO.toString());
        if (resultDTO.getResultCode().equals(PlatformReturnCode.PLU200.name())) {
            String jsonStr  = JSON.toJSONString(resultDTO.getData());
            return new ResultDTO(AuthReturnCode.AU201.name(),
                    AuthReturnCode.AU201.getMsg(),
                    new AuthReturnDTO(JSON.parseObject(jsonStr,UserDTO.class)));
        }
        return new ResultDTO(AuthReturnCode.AU404.name(),
                AuthReturnCode.AU404.getMsg(),
                "");
    }
}
