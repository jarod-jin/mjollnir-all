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
@Table(name = "code_classify")
public class CodeClassify extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2746250479615051848L;

    @Column(columnDefinition = "varchar(30) default null comment '分类名'")
    private String classifyName;

    @Column(columnDefinition = "varchar(30) default null comment '应用分类'")
    private String appClassify;

}
