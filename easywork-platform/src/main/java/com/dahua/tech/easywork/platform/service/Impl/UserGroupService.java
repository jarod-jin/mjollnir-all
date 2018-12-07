package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.api.enums.UserGroupType;
import com.dahua.tech.easywork.platform.entity.UserGroup;
import com.dahua.tech.easywork.platform.entity.UserOutside;
import com.dahua.tech.easywork.platform.entity.relationship.UserGroupMapping;
import com.dahua.tech.easywork.platform.repository.UserGroupMappingRepository;
import com.dahua.tech.easywork.platform.repository.UserGroupRepository;
import com.dahua.tech.easywork.platform.service.IUserGroupService;
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
public class UserGroupService implements IUserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserGroupMappingRepository userGroupMappingRepository;


    @Override
    public List<UserGroup> findAllGroupByUserName(String username) {
        return userGroupRepository.findAllByUserName(username);
    }

    @Override
    public UserGroupMapping addUserToGroup(UserGroupMapping ugp) {
        List<UserGroupMapping> mappingList = userGroupMappingRepository.findAll(Example.of(new UserGroupMapping(ugp.getUserName(),ugp.getGroupId())));
        if (!mappingList.isEmpty()) {
            return mappingList.get(0);
        }
        Optional<UserGroup> optGroup = userGroupRepository.findById(ugp.getGroupId());
        if (optGroup.isPresent() && optGroup.get().getIsDel()==0 && optGroup.get().getGroupType().equals(UserGroupType.END.name())){
           return userGroupMappingRepository.save(ugp);
        }
        return ugp;
    }
}
