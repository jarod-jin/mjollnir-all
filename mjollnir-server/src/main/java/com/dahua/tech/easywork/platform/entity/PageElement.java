package com.dahua.tech.easywork.platform.entity;

import com.dahua.tech.easywork.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "page_element")
public class PageElement extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2406247213851298497L;

    @Column(columnDefinition = "varchar(20) default null comment '页面元素编码'")
    private String elementCode;

    @Column(columnDefinition = "varchar(100) default null comment '页面元素描述'")
    private String elementName;

    @Column(columnDefinition = "varchar(50) default null comment 'Url路径'")
    private String urlPath;

    @Column(columnDefinition = "varchar(20) default null comment '元素分类：Page-页面；Memu-菜单；Module-模块；Operation-操作'")
    private String elementType;

    @Column(columnDefinition = "tinyint default null comment '父节点id'")
    private Integer parentId;
}
