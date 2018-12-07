package com.dahua.tech.easywork.platform.repository;

import com.dahua.tech.easywork.platform.entity.relationship.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther jarod.jin 2018/12/7
 */
@Transactional
@Component
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping,Long> {
}
