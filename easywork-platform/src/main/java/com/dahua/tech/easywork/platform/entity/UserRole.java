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
@Table(name = "user_role")
public class UserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -605603986398745589L;

    @Column(columnDefinition = "varchar(50) default null comment '角色名'")
    private String roleName;

    @Column(columnDefinition = "varchar(30) default null comment '角色分类'")
    private String roleClassify;

}
