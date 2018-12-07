package com.dahua.tech.easywork.platform.service;

import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;

public interface IUserOutsideService {

    ResultDTO register(UserDTO userRegister);

    ResultDTO findByRole(Long roleId);

    ResultDTO queryUserOutside(UserDTO userDTO);
}
