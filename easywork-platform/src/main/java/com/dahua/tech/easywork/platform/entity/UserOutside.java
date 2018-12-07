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
@Table(name = "user_outside")
public class UserOutside extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6104802059959823457L;

    @Column(columnDefinition=("varchar(20) default null comment '用户名'"))
    private String userName;

    @Column(columnDefinition=("varchar(20) not null comment '联系电话'"))
    private String userTel;

    @Column(columnDefinition=("varchar(20) default null comment '显示名'"))
    private String nickName;

    @Column(columnDefinition=("varchar(128) not null comment '密码'"))
    private String password;

    @Column(columnDefinition=("varchar(128) default null comment '上一次密码'"))
    private String passwordLatest;

    @Column(columnDefinition=("varchar(50) default null comment '邮箱'"))
    private String email;

    @Column(columnDefinition=("varchar(10) default null comment '用户类型'"))
    private String userType;

}
