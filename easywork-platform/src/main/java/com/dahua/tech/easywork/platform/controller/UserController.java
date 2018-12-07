package com.dahua.tech.easywork.platform.controller;

import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.platform.service.IUserOutsideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserOutsideService userOutsideService;

    /**
     * 用户注册
     * @param userRegister
     * @return
     */
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO register(UserDTO userRegister){
        return userOutsideService.register(userRegister);
    }

    @RequestMapping(value = "/queryuser",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO queryUserByParams(@RequestBody UserDTO userDTO){
        log.info(userDTO.toString());
        return userOutsideService.queryUserOutside(userDTO);
    }

    @RequestMapping(value = "/findByRole",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO findByRole(Long roleId) { return userOutsideService.findByRole(roleId);
    }

}
