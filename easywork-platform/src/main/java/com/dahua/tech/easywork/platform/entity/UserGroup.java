package com.dahua.tech.easywork.platform.entity;

import com.dahua.tech.easywork.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "user_group")
public class UserGroup extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4953518615003624238L;

    @Column(columnDefinition = "varchar(20) default null comment '用户组名'")
    private String groupName;

    @Column(columnDefinition = "varchar(5) not null  default 'end' comment '用户组类型：end-最终部门可以挂人员的，root-公司级部门，mid-中间部门' ")
    private String groupType;

    @Column(columnDefinition = "bigint(19) comment '父用户组id' ")
    private Long parentId;

}
