package com.dahua.tech.easywork.platform.entity;

import com.dahua.tech.easywork.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "code_content")
public class CodeContent extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8386838075400351506L;

    @Column(columnDefinition = "varchar(20) default null comment '键'")
    private String codeKey;

    @Column(columnDefinition = "varchar(20) default null comment '值'")
    private String codeValue;

    @Column(columnDefinition = "bigint(19) not null comment '类型id'")
    private Long codeClassifyId;
}
