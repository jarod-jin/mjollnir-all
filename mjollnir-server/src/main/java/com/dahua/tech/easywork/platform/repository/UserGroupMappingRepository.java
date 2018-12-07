package com.dahua.tech.easywork.platform.repository;

import com.dahua.tech.easywork.platform.entity.relationship.UserGroupMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * @auther jarod.jin 2018/12/6
 */
@Transactional
@Component
public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping,Long> {

}
