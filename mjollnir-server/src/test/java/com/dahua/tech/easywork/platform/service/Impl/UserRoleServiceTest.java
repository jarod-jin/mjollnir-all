package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.platform.entity.UserRole;
import com.dahua.tech.easywork.platform.entity.relationship.UserRoleMapping;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther jarod.jin 2018/12/7
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRoleServiceTest {

    @Autowired
    UserRoleService userRoleService;

    @Test
    @DisplayName("通过Username查询UserRole,有角色")
    void findAllRoleByUserName_has() {
        List<UserRole> list = userRoleService.findAllRoleByUserName("45216");
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("通过Username查询UserRole,没角色")
    void findAllRoleByUserName_not_has() {
        List<UserRole> list = userRoleService.findAllRoleByUserName("900000");
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("通过添加用户Role角色")
    void addUserToRole() {
        UserRoleMapping roleMapping = new UserRoleMapping();
        roleMapping.setRoleId(1L);
        roleMapping.setCreator("45216");
        roleMapping.setUserName("45216");
        roleMapping = userRoleService.addUserToRole(roleMapping);
        assertTrue(roleMapping.getId()>0);
    }
}