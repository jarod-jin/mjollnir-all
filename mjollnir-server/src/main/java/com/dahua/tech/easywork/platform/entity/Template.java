package com.dahua.tech.easywork.platform.entity;

import com.dahua.tech.easywork.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "template")
public class Template extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2710153053427392206L;

    @Column(columnDefinition = "varchar(30) default null comment '应用模板分类'")
    private String appClassify;

    @Column(columnDefinition = "varchar(100) default null comment '模板内容'")
    private String modelContent;

    @Column(columnDefinition = "varchar(20) default null comment '模板名'")
    private String modelName;
}
