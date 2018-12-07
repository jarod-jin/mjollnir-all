package com.dahua.tech.easywork.platform.entity.relationship;

import com.dahua.tech.easywork.core.entity.SuperBase;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "role_group_mapping")
public class RoleGroupMapping extends SuperBase {

    @Column(columnDefinition = "bigint(19) comment '用户组id'")
    private Long groupId;

    @Column(columnDefinition = "bigint(19) comment '角色id'")
    private Long roleId;


}
