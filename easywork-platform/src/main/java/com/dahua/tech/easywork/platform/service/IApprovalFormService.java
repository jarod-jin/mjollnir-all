package com.dahua.tech.easywork.platform.service;

import com.dahua.tech.easywork.api.dto.platform.ApprovalFormDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.platform.entity.ApprovalForm;

public interface IApprovalFormService {
    ResultDTO submit(ApprovalFormDTO approvalForm);
}
