package com.dahua.tech.easywork.platform.service;

import com.dahua.tech.easywork.platform.entity.UserRole;
import com.dahua.tech.easywork.platform.entity.relationship.UserRoleMapping;

import java.util.List;

/**
 * @auther jarod.jin 2018/12/6
 */
public interface IUserRoleService {

    List<UserRole> findAllRoleByUserName(String username);

    UserRoleMapping addUserToRole(UserRoleMapping urp);
}
