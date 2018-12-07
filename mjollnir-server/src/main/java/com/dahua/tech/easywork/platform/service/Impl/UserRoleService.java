package com.dahua.tech.easywork.platform.service.Impl;


import com.dahua.tech.easywork.platform.entity.UserRole;
import com.dahua.tech.easywork.platform.entity.relationship.UserRoleMapping;
import com.dahua.tech.easywork.platform.repository.UserRoleMappingRepository;
import com.dahua.tech.easywork.platform.repository.UserRoleRepository;
import com.dahua.tech.easywork.platform.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @auther jarod.jin 2018/12/6
 */
@Slf4j
@Service
public class UserRoleService implements IUserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Override
    public List<UserRole> findAllRoleByUserName(String username) {
        return userRoleRepository.findAllByUserName(username);
    }

    @Override
    public UserRoleMapping addUserToRole(UserRoleMapping urp) {
        List<UserRoleMapping> mappingList = userRoleMappingRepository.findAll(Example.of(new UserRoleMapping(urp.getUserName(),urp.getRoleId())));
        if (!mappingList.isEmpty()) {
            return mappingList.get(0);
        }
        Optional<UserRole> optGroup = userRoleRepository.findById(urp.getRoleId());
        if (optGroup.isPresent() && optGroup.get().getIsDel()==0 ){
            return userRoleMappingRepository.save(urp);
        }
        return urp;
    }
}
