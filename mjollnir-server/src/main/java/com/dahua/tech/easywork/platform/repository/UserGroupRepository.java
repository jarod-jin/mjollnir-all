package com.dahua.tech.easywork.platform.repository;

import com.dahua.tech.easywork.platform.entity.UserGroup;
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
public interface UserGroupRepository extends JpaRepository<UserGroup,Long> {

    @Query(value = "select g.* from user_group_mapping m left join user_group g on g.id = m.group_id where m.user_name=:username",
            nativeQuery = true)
    List<UserGroup> findAllByUserName(@Param("username")String username);
}
