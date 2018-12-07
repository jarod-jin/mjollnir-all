package com.dahua.tech.easywork.platform.controller;

import com.dahua.tech.easywork.api.dto.platform.ApprovalFormDTO;
import com.dahua.tech.easywork.core.dto.ResultDTO;
import com.dahua.tech.easywork.platform.service.IApprovalFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApprovalFormController {
    @Autowired
    IApprovalFormService approvalFormService;

    @RequestMapping(value = "/apply",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO submit(@RequestBody ApprovalFormDTO approvalForm){
        ResultDTO resultDTO = approvalFormService.submit(approvalForm);
        return resultDTO;
    }
}
