package com.dahua.tech.easywork.platform.entity;

import com.dahua.tech.easywork.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "approval_form")
public class ApprovalForm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5724472829195806167L;

    @Column(columnDefinition = ("varchar(20) default null comment '审批单号'"))
    private String serialNo;

    @Column(columnDefinition = ("int default null comment '审批单类型'"))
    private Integer formType;

    @Column(columnDefinition = ("varchar(100) default null comment '审批单位'"))
    private String applicationCompany;

    @Column(columnDefinition = ("bigint default null comment '业务单id'"))
    private long businessId;

    @Column(columnDefinition = ("varchar(20) default null comment '流程号'"))
    private String workflowNo;

    @Column(columnDefinition = ("varchar(20) default null comment '申请人'"))
    private String applicant;

    @Column(columnDefinition = ("date default null comment '申请日期'"))
    private Date applicationDate;

    @Column(columnDefinition = ("varchar(20) default null comment '联系人电话'"))
    private String applicantTel;
}
