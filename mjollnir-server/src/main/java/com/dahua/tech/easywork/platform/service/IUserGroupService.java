package com.dahua.tech.easywork.platform.service;

import com.dahua.tech.easywork.platform.entity.UserGroup;
import com.dahua.tech.easywork.platform.entity.UserOutside;
import com.dahua.tech.easywork.platform.entity.relationship.UserGroupMapping;

import java.util.List;

/**
 * @auther jarod.jin 2018/12/6
 */
public interface IUserGroupService {

    List<UserGroup> findAllGroupByUserName(String username);

    UserGroupMapping addUserToGroup(UserGroupMapping ugp);

}
