package com.dahua.tech.easywork.platform;

import com.dahua.tech.easywork.api.dto.platform.ApprovalFormDTO;
import com.dahua.tech.easywork.api.dto.platform.UserDTO;
import com.dahua.tech.easywork.platform.entity.UserOutside;
import com.dahua.tech.easywork.platform.repository.UserOutsideRepository;
import com.dahua.tech.easywork.platform.service.IApprovalFormService;
import com.dahua.tech.easywork.platform.service.IUserOutsideService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRegisterTest {
    @Autowired
    IUserOutsideService userOutsideService;
    @Autowired
    UserOutsideRepository userOutsideRepository;
    @Autowired
    IApprovalFormService approvalFormService;


    @Test
    void save(){
        UserDTO userRegisterDTO = new UserDTO();
        String authCode = "1234";
        userRegisterDTO.setPassword("abc123");
        userRegisterDTO.setTel("18899999966");
        userOutsideService.register(userRegisterDTO);
    }
    @Test
    void findAll(){
        List<UserOutside> all = userOutsideRepository.findAll();
        for (UserOutside userOutside : all) {
            System.out.println(userOutside);
        }
    }
    @Test
    void saveApprovalForm(){
        ApprovalFormDTO approvalFormDTO = new ApprovalFormDTO();
        approvalFormDTO.setApplicant("wjt");
        approvalFormDTO.setApplicantTel("15295170219");
        approvalFormDTO.setBusinessId(12345699);
        approvalFormDTO.setApplicationCompany("大华");
        approvalFormDTO.setFormType(123);
        approvalFormDTO.setWorkflowNo("123abc");
        approvalFormDTO.setApplicationDate(new Date());
        approvalFormDTO.setSerialNo("123abc");
        approvalFormService.submit(approvalFormDTO);

    }
    @Test
    void login(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("wb_132");
        userDTO.setPassword("123456");
        String authCode = "1234";
        userOutsideService.queryUserOutside(userDTO);
    }
}
