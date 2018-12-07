package com.dahua.tech.easywork.platform.repository;

import com.dahua.tech.easywork.platform.entity.UserOutside;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public interface UserOutsideRepository extends JpaRepository<UserOutside,Long> {
    /**
     * 根据用户联系电话判断是否存在该用户
     * @param tel
     * @return
     */
    boolean existsByUserTel(String tel);
    /**
     * 用户登录1
     */
    UserOutside findByUserTel(String tel);

    /**
     * 用户登录2
     * @param username
     * @return
     */
    UserOutside findByUserName(String username);
}
