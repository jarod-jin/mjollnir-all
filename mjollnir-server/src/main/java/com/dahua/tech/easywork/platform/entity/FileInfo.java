package com.dahua.tech.easywork.platform.entity;

import com.dahua.tech.easywork.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "file_info")
public class FileInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6580442689297751609L;

    @Column(columnDefinition = "varchar(50) default null comment '原始文件名'")
    private String originFileName;

    @Column(columnDefinition = "varchar(200) default null comment '路径'")
    private String docUrl;

    @Column(columnDefinition = "varchar(250) default null comment '全路径'")
    private String entireUrl;
}
