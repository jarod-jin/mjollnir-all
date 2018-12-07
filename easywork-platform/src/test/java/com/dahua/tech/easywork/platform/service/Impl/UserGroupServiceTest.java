package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.platform.entity.UserGroup;
import com.dahua.tech.easywork.platform.entity.relationship.UserGroupMapping;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther jarod.jin 2018/12/6
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserGroupServiceTest {

    @Autowired
    UserGroupService userGroupService;

    @Test
    @DisplayName("通过Username查询UserGroup，有部门")
    void findAllGroupByUser_has() {
        List<UserGroup> list = userGroupService.findAllGroupByUserName("45216");
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("通过Username查询UserGroup，没部门")
    void findAllGroupByUser_not_has() {
        List<UserGroup> list = userGroupService.findAllGroupByUserName("9000000");
        assertTrue(list.isEmpty());
    }


    @Test
    @DisplayName("用户添加END群组")
    void addUserToGroup_with_end_group() {
        UserGroupMapping groupMapping = new UserGroupMapping();
        groupMapping.setGroupId(1L);
        groupMapping.setCreator("45216");
        groupMapping.setUserName("45216");
        groupMapping = userGroupService.addUserToGroup(groupMapping);
        assertTrue(groupMapping.getId()>0);
    }

    @Test
    @DisplayName("用户添加不是END群组")
    void addUserToGroup_without_end_group() {
        UserGroupMapping groupMapping = new UserGroupMapping();
        groupMapping.setGroupId(2L);
        groupMapping.setUserName("45216");
        assertNull(userGroupService.addUserToGroup(groupMapping));
    }

}