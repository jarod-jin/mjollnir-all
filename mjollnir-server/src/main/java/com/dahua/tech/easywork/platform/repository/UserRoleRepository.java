package com.dahua.tech.easywork.platform.repository;

import com.dahua.tech.easywork.platform.entity.UserGroup;
import com.dahua.tech.easywork.platform.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther jarod.jin 2018/12/6
 */
@Transactional
@Component
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Query(value = "select r.* from user_role_mapping m left join user_role r on r.id = m.role_id where m.user_name=:username",
            nativeQuery = true)
    List<UserRole> findAllByUserName(@Param("username")String username);
}
