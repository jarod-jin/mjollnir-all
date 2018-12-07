package com.dahua.tech.easywork.platform.entity.relationship;

import com.dahua.tech.easywork.core.entity.SuperBase;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "template_permission")
public class TemplatePermission extends SuperBase {

    @Column(columnDefinition = "bigint(19) comment '应用模板数据id'")
    private Long templateId;

    @Column(columnDefinition = "bigint(19) comment '权限id'")
    private Long roleId;
}
