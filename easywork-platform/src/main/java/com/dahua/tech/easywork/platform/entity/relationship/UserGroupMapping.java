package com.dahua.tech.easywork.platform.entity.relationship;


import com.dahua.tech.easywork.core.entity.SuperBase;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "user_group_mapping")
public class UserGroupMapping extends SuperBase {

    @Column(columnDefinition = "varchar(20) comment '用户名'")
    private String userName;

    @Column(columnDefinition = "bigint(19) comment '用户组id'")
    private Long groupId;

    public UserGroupMapping(){

    }

    public UserGroupMapping(String userName, Long groupId) {
        this.userName = userName;
        this.groupId = groupId;
    }
}
