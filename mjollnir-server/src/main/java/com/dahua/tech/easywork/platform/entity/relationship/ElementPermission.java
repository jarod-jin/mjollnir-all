package com.dahua.tech.easywork.platform.entity.relationship;

import com.dahua.tech.easywork.core.entity.SuperBase;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "element_permission")
public class ElementPermission extends SuperBase {

    @Column(columnDefinition = "bigint(19) comment '页面元素id'")
    private Long ElementId;

    @Column(columnDefinition = "bigint(19) comment '权限id'")
    private Long roleId;
}
