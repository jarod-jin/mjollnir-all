package com.dahua.tech.easywork.platform.entity.relationship;


import com.dahua.tech.easywork.core.entity.SuperBase;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user_role_mapping")
public class UserRoleMapping extends SuperBase implements Serializable {

    @Column(columnDefinition = "varchar(20) comment '用户名'")
    private String userName;

    @Column(columnDefinition = "bigint(19) comment '角色id'")
    private Long roleId;

    public UserRoleMapping(){};

    public UserRoleMapping(String userName, Long roleId) {
        this.userName = userName;
        this.roleId = roleId;
    }
}
