package com.dahua.tech.easywork.platform.entity.relationship;

import com.dahua.tech.easywork.core.entity.SuperBase;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "file_permission")
public class FilePermission extends SuperBase {

    @Column(columnDefinition = "bigint(19) comment '文件id'")
    private Long fileInfoId;

    @Column(columnDefinition = "bigint(19) comment '权限id'")
    private Long roleId;
}
