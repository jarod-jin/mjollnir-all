package com.dahua.tech.easywork.platform.service.Impl;

import com.dahua.tech.easywork.api.dto.platform.ApprovalFormDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.platform.entity.ApprovalForm;
import com.dahua.tech.easywork.platform.repository.ApprovalFormRepository;
import com.dahua.tech.easywork.platform.service.IApprovalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalFormServiceImpl implements IApprovalFormService {
    @Autowired
    ApprovalFormRepository approvalFormRepository;

    @Override
    public ResultDTO submit(ApprovalFormDTO approvalForm) {

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultCode("200");

        ApprovalForm form = new ApprovalForm();
        form.setApplicant(approvalForm.getApplicant());
        form.setApplicantTel(approvalForm.getApplicantTel());
        form.setApplicationCompany(approvalForm.getApplicationCompany());
        form.setApplicationDate(approvalForm.getApplicationDate());
        form.setBusinessId(approvalForm.getBusinessId());
        form.setFormType(approvalForm.getFormType());
        form.setSerialNo(approvalForm.getSerialNo());
        form.setWorkflowNo(approvalForm.getWorkflowNo());

        approvalFormRepository.save(form);
        resultDTO.setResultMessage("提交成功");
        resultDTO.setData(null);
        return resultDTO;
    }
}
